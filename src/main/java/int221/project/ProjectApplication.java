package int221.project;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import int221.project.services.FileStoreServices;

@SpringBootApplication
//public class ProjectApplication implements CommandLineRunner {
public class ProjectApplication {
//	@Resource
//	FileStoreServices storeService;

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}
	
//	@Override
//	  public void run(String... arg) throws Exception {
//	    storeService.deleteAll();
//	    storeService.init();
//	  }

}
