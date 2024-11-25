import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class Main {

    private static String BASE_URL = "https://fakerapi.it";

    public static void main(String[] args) {
        System.out.println("Hello project");

        WebClient webClient = WebClient.builder().baseUrl(BASE_URL).build();

        String responseBody;

//        Mono<String> flux =
//          responseBody
                webClient // Reassign the result to a String
                .get()
                .uri("/api/v1/persons")
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
                .uri("/api/v1/persons")
                .retrieve()
                .bodyToMono(String.class) // Bind the returned JSON body to a String
                .block();

//        flux
//                .subscribe(c -> {
//            System.out.println("dd");
//            System.out.println(c);
//        });

//        flux.subscribe(System.out::println);

//        ObjectMapper mapper = new ObjectMapper();
//
//        try {
//            var result = mapper.readValue(responseBody, FakeBooksDAO.class);
//            System.out.println(result);
//        } catch (JsonProcessingException e) {
//            System.err.println(e);
//        }


//        System.out.println(responseBody);



    }

    private static Mono<? extends Throwable> handleErrorResponse(HttpStatusCode statusCode) {

        // Handle non-success status codes here (e.g., logging or custom error handling)
        return Mono.error(new Exception("Failed to fetch employee. Status code: " + statusCode));
    }

}
