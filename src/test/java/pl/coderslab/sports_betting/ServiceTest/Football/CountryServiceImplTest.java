package pl.coderslab.sports_betting.ServiceTest.Football;

import org.junit.Before;
import org.junit.Test;
import pl.coderslab.sports_betting.Entity.Football.Country;
import pl.coderslab.sports_betting.Repository.General.CountryRepository;
import pl.coderslab.sports_betting.Service.Football.Service.CountryService;
import pl.coderslab.sports_betting.Service.Football.ServiceImpl.CountryServiceImpl;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CountryServiceImplTest {

    private CountryRepository countryRepository;
    private CountryService countryService;

    @Before
    public void setUp() {

        countryRepository = mock(CountryRepository.class);
        countryService = new CountryServiceImpl(countryRepository);
    }

    @Test
    public void shouldFindAllCountries(){
        Country country1 = new Country();
        country1.setName("Cosiomia");
        Country country2 = new Country();
        country2.setName("Blablamia");
        List<Country> fakeCountries = new ArrayList<>();
        fakeCountries.add(country1);
        fakeCountries.add(country2);

        when(countryRepository.findAll()).thenReturn(fakeCountries);

        List<Country> countries = countryService.allCountries();

        assertThat(countries, notNullValue());
        assertThat(countries.size(), is(2));
    }

    @Test
    public void shouldOneCountryById(){
        Country country1 = new Country();
        country1.setName("Cosiomia");
        country1.setId(1L);

        when(countryRepository.getOne(1L)).thenReturn(country1);

        Country result = countryService.getCountryById(1L);

        assertThat(result, notNullValue());
        assertThat(result.getName(), is("Cosiomia"));
    }


}
