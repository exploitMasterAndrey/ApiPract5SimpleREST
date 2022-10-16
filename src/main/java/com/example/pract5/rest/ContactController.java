package com.example.pract5.rest;

import com.example.pract5.model.Contact;
import com.example.pract5.service.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContactController {
    @Autowired
    private ContactService contactService;

    @Operation(
            tags = "Получить все контакты",
            summary = "Будут получены все контакты из БД",
            description = "Нажмите кнопку \"Try it out\" для получения всех контактов",
            responses = {@ApiResponse(responseCode = "200",
                    content = @Content(schema = @Schema(implementation = Contact.class),mediaType = MediaType.APPLICATION_JSON_VALUE
                            ,examples = {@ExampleObject(name = "Пример",value ="Объект контакта")}),description = "Контакт успешно изменён"),
                    @ApiResponse(responseCode = "500",description = "Ошибка сервера",content = @Content())}
    )
    @GetMapping("/getAll")
    public List<Contact> getAll(){
        return contactService.getAll();
    }

    @PostMapping("/create")
    @Operation(
            tags = "Сохранить контакт",
            summary = "Контакт сохранится в базу данных",
            description = "Нажмите кнопку \"Try it out\" и вводите данные о контакте",

            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Описание контакта",
                    content = @Content(
                            schemaProperties = {
                                    @SchemaProperty(name = "phone",schema = @Schema(name = "contact")),
                                    @SchemaProperty(name = "name",schema = @Schema(name = "contact"))
                            }
                    )),
            responses = {@ApiResponse(responseCode = "200", description = "Контакт сохранён"),
                    @ApiResponse(responseCode = "500",description = "Ошибка сервера",content = @Content())}
    )
    public void createNewContact(@RequestBody Contact contact){contactService.createContact(contact);}

    @PutMapping("/update")
    @Operation(
            tags = "Обновить данные о контакте",
            summary = "Данные о контакте будут удалены",
            description = "Нажмите кнопку \"Try it out\" и вводите данные о контакте, который хотите изменить",

            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Описание нашего контакта",
                    content = @Content(
                            schemaProperties = {
                                    @SchemaProperty(name = "id", schema = @Schema(name = "contact")),
                                    @SchemaProperty(name = "phone",schema = @Schema(name = "contact")),
                                    @SchemaProperty(name = "name",schema = @Schema(name = "contact"))
                            }
                    )),
            responses = {@ApiResponse(responseCode = "200", description = "Контакт успешно изменен"),
                    @ApiResponse(responseCode = "500",description = "Ошибка сервера",content = @Content())}
    )
    public void updateContact(@RequestBody Contact contact){
        contactService.updateContact(contact);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(
            tags = "Удалить контакт",
            summary = "Контакт удалится из базы данных",
            description = "Нажмите кнопку \"Try it out\" и вводите данные о контакте, который хотите удалить",

            responses = {@ApiResponse(responseCode = "200", description = "Контакт успешно удалён"),
                    @ApiResponse(responseCode = "500",description = "Ошибка сервера",content = @Content())}
    )
    public void deleteContact(@PathVariable Long id){
        contactService.deleteContact(id);
    }
}
