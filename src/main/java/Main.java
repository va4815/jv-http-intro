import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.FakePersonsDAO;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class Main {

//    private static String BASE_URL = "https://fakerapi.it";

    private static String BASE_URL = "https://api.schiphol.nl/public-flights";
    public static void main(String[] args) {
        System.out.println("Hello project");

        WebClient webClient = WebClient.builder().baseUrl(BASE_URL).build();

        String responseBody;

//        Mono<String> flux =
//          responseBody
                webClient // Reassign the result to a String
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/flights")
//                        .queryParam("includedelays", "false")
//                        .queryParam("page", 0)
//                        .queryParam("sort", "%2BscheduleTime")
                        .build()
                )
                        .header("Accept", "application/json")
                        .header("app_id", "6089f8cc")
                        .header("app_key", "e17f374f4d115b8fad35605927821d72")
                        .header("ResourceVersion", "v4")
                .accept(MediaType.APPLICATION_JSON)

                .retrieve()
//                .toEntity(FakeBooksDAO.class)
//                .bodyToFlux(FakeBooksDAO.class)
                .onStatus(httpStatus -> !httpStatus.is2xxSuccessful(),
                        clientResponse -> handleErrorResponse(clientResponse.statusCode()))
                .bodyToMono(String.class)
//                .publish(s -> {
//                    System.out.println(s);
//                    return s;
//                })
                .map(d -> {
                    System.out.println(d);
                    return d;
                })
                .subscribe(s -> {
                    System.out.println(s);
                })

//                .block()
        ;


        responseBody = webClient // Reassign the result to a String
                .get()
                .uri("/flights")
                .retrieve()
                .bodyToMono(String.class) // Bind the returned JSON body to a String
                .block();

//        flux
//                .subscribe(c -> {
//            System.out.println("dd");
//            System.out.println(c);
//        });

//        flux.subscribe(System.out::println);

        ObjectMapper mapper = new ObjectMapper();

        try {
            var result = mapper.readValue(responseBody, FakePersonsDAO.class);
            System.out.println(result);
        } catch (JsonProcessingException e) {
            System.err.println(e);
        }


//        System.out.println(responseBody);



    }

    private static Mono<? extends Throwable> handleErrorResponse(HttpStatusCode statusCode) {

        // Handle non-success status codes here (e.g., logging or custom error handling)
        return Mono.error(new Exception("Failed to fetch employee. Status code: " + statusCode));
    }

}
