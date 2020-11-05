package pl.coderslab.charity.donation;

import com.github.javafaker.Address;
import com.github.javafaker.Faker;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import pl.coderslab.charity.category.Category;
import pl.coderslab.charity.institution.Institution;
import pl.coderslab.charity.institution.InstitutionRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Donation Service Specification")
class DonationServiceTest {

    InstitutionRepository institutionRepository;
    DonationRepository donationRepository;
    DonationService donationService;

    @BeforeEach
    void setUp() {
        donationRepository = Mockito.mock(DonationRepository.class);
        institutionRepository = Mockito.mock(InstitutionRepository.class);
        donationService = new DonationService(donationRepository);
    }

    @Nested
    @DisplayName("When saving donation")
    class SaveDonation {

        Donation donation;
        Institution institution;
        Category category;

        @BeforeEach
        void setUp() {
            Faker faker = new Faker();
            Address address = faker.address();
            institution = new Institution();
            institution.setName(faker.company().name());
            institution.setDescription(faker.book().title());
            institution.setId(faker.number().randomNumber());

            category = new Category();
            category.setName(faker.ancient().god());
            category.setId(faker.number().randomNumber());

            donation = Donation.builder()
                    .city(address.city())
                    .zipCode(address.zipCode())
                    .pickUpComment(faker.chuckNorris().fact())
                    .quantity(faker.number().numberBetween(0, 100))
                    .pickUpDate(LocalDate.now().plusDays(1))
                    .pickUpTime(LocalTime.now())
                    .street(address.streetAddress())
                    .institution(institution)
                    .categories(List.of(category, category))
                    .build();
        }

        @Test
        @DisplayName(" - should save donation")
        void test1() {
            ArgumentCaptor<Donation> donationCaptor = ArgumentCaptor.forClass(Donation.class);
            Mockito.when(donationRepository.save(donationCaptor.capture())).thenReturn(donation);

            donationService.saveDonation(donation);

            Donation savedDonation = donationCaptor.getValue();
            Mockito.verify(donationRepository, Mockito.atLeastOnce()).save(savedDonation);
            Assertions.assertThat(savedDonation)
                    .isNotNull()
                    .isEqualTo(donation)
                    .hasFieldOrPropertyWithValue("city", donation.getCity())
                    .hasNoNullFieldsOrPropertiesExcept("id");
        }

        @Nested
        @DisplayName("Things go wrong when")
        class ThingsGoWrongWhen {

            @Test
            @DisplayName(" - institution doesn't exists then throw exception")
            void test1() {
                institution.setId(-1L);
                Mockito.when(institutionRepository.findById((Long) institution.getId())).thenReturn(Optional.empty());

                Assertions.assertThatThrownBy(() -> donationService.saveDonation(donation))
                        .hasMessageContaining("id = " + institution.getId())
                        .isInstanceOf(IllegalArgumentException.class);
            }

        }

    }



}