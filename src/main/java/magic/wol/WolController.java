package magic.wol;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WolController {
	
	
	@GetMapping("/wake_pc12")
	public String wakePc() {
		
		try {
			
			System.out.println(new File("command.txt").toPath());
			List<String> commands = Files.readAllLines(new File("command.txt").toPath());
			for(String command : commands)
			{
				Process p = Runtime.getRuntime().exec(command);
				BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
				String output = null;
				while((output = reader.readLine()) != null)
				{
					System.out.println(output);
				}
				reader.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return "error " + e;
		}
		
		
		return "success";
	}
	
	
	
}
