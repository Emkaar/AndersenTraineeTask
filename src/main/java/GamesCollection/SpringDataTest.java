package GamesCollection;

import GamesCollection.config.DataConfig;
import GamesCollection.service.UserDataService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringDataTest {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(DataConfig.class);
        UserDataService service = context.getBean(UserDataService.class);
        System.out.println(service.getAll());
    }
}
