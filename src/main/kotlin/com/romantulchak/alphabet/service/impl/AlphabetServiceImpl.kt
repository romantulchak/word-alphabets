package com.romantulchak.alphabet.service.impl

import com.romantulchak.alphabet.consts.AppConst
import com.romantulchak.alphabet.dto.AlphabetDTO
import com.romantulchak.alphabet.exception.AlphabetAlreadyExistsException
import com.romantulchak.alphabet.exception.LanguageNotFoundException
import com.romantulchak.alphabet.model.Alphabet
import com.romantulchak.alphabet.model.Letter
import com.romantulchak.alphabet.repository.AlphabetRepository
import com.romantulchak.alphabet.repository.LanguageRepository
import com.romantulchak.alphabet.request.CreateAlphabetRequest
import com.romantulchak.alphabet.service.AlphabetService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import javax.transaction.Transactional

const val REGEX_STATEMENT = "code: [a-zA-Z]*\talphabet:"
const val REGEX_SEMICOLON_COMA = "[;,]"

@Service
class AlphabetServiceImpl(
    private val alphabetRepository: AlphabetRepository,
    private val languageRepository: LanguageRepository,
    @Value(value = "\${alphabet.files.path}")
    private val filePath: String
) : AlphabetService {


    /**
     * {@inheritDoc}
     */
    @Transactional
    override fun createAlphabetForLanguage(createAlphabetRequests: List<CreateAlphabetRequest>) {
        createAlphabetRequests.forEach {
            val language = languageRepository.findByCode(it.languageCode)
            if (language === null) {
                throw LanguageNotFoundException(it.languageCode)
            }
            if (alphabetRepository.existsByCode(it.languageCode)){
                throw AlphabetAlreadyExistsException(it.languageCode)
            }
            val alphabet = Alphabet(null, getLetters(it), language, language.code)
            alphabetRepository.save(alphabet)
        }
    }

    /**
     * {@inheritDoc}
     */
    override fun getAlphabetForLanguage(languageCode: String): AlphabetDTO {
        val alphabet = alphabetRepository.findAllByLanguageCode(languageCode)
        return AlphabetDTO(alphabet.letters)
    }

    /**
     * {@inheritDoc}
     */
    override fun createAlphabetFromFile(file: MultipartFile) {
        val uuid: UUID = UUID.randomUUID()
        file.inputStream.use {
            Files.copy(it, Paths.get("$filePath/$uuid"))
        }
        val alphabetRequestFromFile = getAlphabetsToCreateFromFile(uuid)
        createAlphabetForLanguage(alphabetRequestFromFile)
    }

    /**
     * Maps letters from client to Letter object
     *
     * @param createAlphabetRequest contains required attributes to create list of letters
     * @return list of letters
     */
    private fun getLetters(createAlphabetRequest: CreateAlphabetRequest): List<Letter> {
        val letters: MutableList<Letter> = arrayListOf()
        createAlphabetRequest.letters.forEach { l ->
            val letter = Letter(
                l.lowercaseChar(),
                l.uppercaseChar(),
                l.code
            )
            letters.add(letter)
        }
        return letters
    }

    /**
     * Get alphabets to creates from file
     *
     * @param uuid to get file by its name
     * @return list of alphabtes to be created
     */
    private fun getAlphabetsToCreateFromFile(uuid: UUID): List<CreateAlphabetRequest> {
        val alphabetRequests = arrayListOf<CreateAlphabetRequest>()
        val fileText = File("$filePath/$uuid")
        fileText.forEachLine { line ->
            val regex = Regex(REGEX_STATEMENT)
            if (line.contains(regex)) {
                val codeAlphabet = line.replace(AppConst.LANGUAGE_CODE_REPLACE, "")
                    .replace(AppConst.ALPHABET_WORD_REPLACE, "")
                    .split(" ")
                val languageCode = codeAlphabet[0]
                val letters = arrayListOf<Char>()
                codeAlphabet[1].replace(Regex(REGEX_SEMICOLON_COMA), "").forEach {
                    letters.add(it)
                }
                val alphabetRequest = CreateAlphabetRequest(letters, languageCode)
                alphabetRequests.add(alphabetRequest)
            }
        }
        return alphabetRequests
    }
}