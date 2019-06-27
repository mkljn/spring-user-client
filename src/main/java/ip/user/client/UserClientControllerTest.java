/*
package ip.user.client;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/test")
public class UserClientControllerTest {
	
	
	RestTemplate restTemplate = new RestTemplate();
	
	@RequestMapping("/{ipaddress}")
	public String findIP (@PathVariable("ipaddress") String ipaddress)
	
	{
		restTemplate.getForEntity("http://localhost:9090/api/{ipaddress}", String.class, ipaddress);
	
		return ipaddress;
		
	}

}
*/