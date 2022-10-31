package nl.duckstudios.pintandpillage;

import nl.duckstudios.pintandpillage.entity.Village;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class VillageTestConfiguration {

    @Bean
    @Primary
    public Village villageObject() {
        return Mockito.mock(Village.class);
    }

}
