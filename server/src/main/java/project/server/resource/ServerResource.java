package project.server.resource;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.server.model.Response;
import project.server.model.Server;
import project.server.service.implementation.ServerServiceImplementation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;
import static project.server.enumeration.Status.SERVER_UP;

@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")

public class ServerResource {
    private final ServerServiceImplementation serverService;

    @RequestMapping("/list")
    public ResponseEntity<Response> getServers() {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("servers", serverService.list(30)))
                        .message("Servers retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @RequestMapping("/ping/{ipAddress}")
    public ResponseEntity<Response> pingServer(@PathVariable("ipAddress") String ipAddress) throws IOException {
        Server server = serverService.ping(ipAddress);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("server", server))
                        .message(server.getStatus() == SERVER_UP ? "Ping success" : "Ping Failed")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @RequestMapping("/save")
    public ResponseEntity<Response> saveServer(@RequestBody @Valid Server server) throws IOException {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("server", serverService.create(server)))
                        .message("Server created")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
    }

        @RequestMapping("/get/{id}")
        public ResponseEntity<Response> getServer(@PathVariable("id") Long id) {
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(now())
                            .data(Map.of("server", serverService.get(id)))
                            .message("Server retrieved")
                            .status(OK)
                            .statusCode(OK.value())
                            .build()
            );
        }
        @DeleteMapping(value = "/delete/{id}")
        @GetMapping
    public ResponseEntity<Response> deleteServer(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("deleted", serverService.get(id)))
                        .message("Server deleted")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
    @GetMapping(path = "/image/{fileName}", produces = IMAGE_PNG_VALUE)
    public byte[] getServerImage(@PathVariable("fileName") String fileName) throws IOException {
        ClassPathResource imageResource = new ClassPathResource("images/" + fileName);
        Path imagePath = Paths.get(imageResource.getURI());
        return Files.readAllBytes(imagePath);
    }
}
