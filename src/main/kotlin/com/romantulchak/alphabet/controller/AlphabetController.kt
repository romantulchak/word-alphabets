package com.romantulchak.alphabet.controller

import com.romantulchak.alphabet.dto.FilePreviewDTO
import com.romantulchak.alphabet.dto.LetterDTO
import com.romantulchak.alphabet.request.CreateAlphabetRequest
import com.romantulchak.alphabet.service.AlphabetService
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import javax.validation.constraints.NotNull

@RestController
@CrossOrigin(value = ["*"], maxAge = 3600L)
@RequestMapping("/api/v1/alphabet")
class AlphabetController(private val alphabetService: AlphabetService) {

    @PostMapping("/create")
    fun create(@NotNull @RequestBody createAlphabetRequest: List<CreateAlphabetRequest>){
        alphabetService.createAlphabetForLanguage(createAlphabetRequest)
    }

    @GetMapping
    fun getAlphabetByLanguage(@RequestParam(value = "languageCode", defaultValue = "en") languageCode: String): List<LetterDTO>{
        return alphabetService.getAlphabetForLanguage(languageCode)
    }

    @PostMapping("/create-from-file")
    fun createFromFile(@RequestPart(value = "file") file: MultipartFile){
        alphabetService.createAlphabetFromFile(file)
    }

    @PostMapping("/preview")
    fun getFilePreview(@RequestPart(value = "file") file: MultipartFile): List<FilePreviewDTO>{
        return alphabetService.getFilePreview(file)
    }

}