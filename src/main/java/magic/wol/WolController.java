package magic.wol;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WolController {
	
	
	@GetMapping("/wake_pc12")
	public String wakePc() {
		
		try {
			
			System.out.println(new File("addresses.txt").toPath());
			List<String> lines = Files.readAllLines(new File("addresses.txt").toPath());
			for(String line : lines)
			{
				String[] split = line.split(" ");
				
				if(split.length == 2)
				{
					String subnet = split[0];
					String macAddress = split[1];
					System.out.println("Subnet used " + subnet);
					System.out.println("Mac Address: " + macAddress);
					Runtime.getRuntime().exec("etherwake -b " + subnet + " " + macAddress);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			return "error " + e;
		}
		
		
		return "success";
	}
	
	
	
}
