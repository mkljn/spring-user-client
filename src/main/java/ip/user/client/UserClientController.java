package ip.user.client;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/v1")
public class UserClientController {

    private HttpHeaders headers;
    RestTemplate restTemplate = new RestTemplate();
 
    private static final String[] IP_HEADER_CANDIDATES = { 
              "X-Forwarded-For", 
              "Proxy-Client-IP", 
              "WL-Proxy-Client-IP",
              "HTTP_X_FORWARDED_FOR", 
              "HTTP_X_FORWARDED", 
              "HTTP_X_CLUSTER_CLIENT_IP", 
              "HTTP_CLIENT_IP",
              "HTTP_FORWARDED_FOR", 
              "HTTP_FORWARDED", 
              "HTTP_VIA", "REMOTE_ADDR" 
    };

    public UserClientController() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
    }

    @RequestMapping(value = "/ipAddress", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Object> getIpAddress(HttpServletRequest request)
            throws IOException {

        String remoteAddress = "";
        if (request != null) {
            remoteAddress = getClientIpAddress(request);
        }
        restTemplate.getForEntity("https://spring-app-server.herokuapp.com/api/{ipaddress}", String.class, remoteAddress);
        return new ResponseEntity<Object>(remoteAddress, headers, HttpStatus.OK);
    }
 
    public static String getClientIpAddress(HttpServletRequest request) {
        for (String header : IP_HEADER_CANDIDATES) {
            String ip = request.getHeader(header);
            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                return ip;
            }
        }
        return request.getRemoteAddr();  
        
    }
    
}
