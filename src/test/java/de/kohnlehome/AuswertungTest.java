package de.kohnlehome;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.within;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuswertungTest {
    private IAuswertung auswertung;
    private ICoronaDaten coronaDatenMock;
    private IEinwohnerDaten einwohnerDatenMock;

    //Arrange
    @BeforeEach
    public void init(){
        coronaDatenMock = mock(ICoronaDaten.class);
        when(coronaDatenMock.getAnzahlInfiziert("Iran")).thenReturn(8100);
        when(coronaDatenMock.getAnzahlInfiziert("Frankreich")).thenReturn(1700);
        when(coronaDatenMock.getAnzahlInfiziert("Nullland")).thenReturn(0);

        when(coronaDatenMock.getAnzahlTote("Iran")).thenReturn(291);
        when(coronaDatenMock.getAnzahlTote("Frankreich")).thenReturn(30);
        when(coronaDatenMock.getAnzahlTote("Nullland")).thenReturn(0);

        einwohnerDatenMock = mock(IEinwohnerDaten.class);
        when(einwohnerDatenMock.getAnzahlEinwohner("Iran")).thenReturn(81000000);
        when(einwohnerDatenMock.getAnzahlEinwohner("Frankreich")).thenReturn(66000000);
        when(einwohnerDatenMock.getAnzahlEinwohner("Nullland")).thenReturn(200);

        auswertung = new Auswertung(coronaDatenMock,einwohnerDatenMock);
    }

    @Test
    public void testIranGetInfizierte(){
        //Act
        double infizierte = auswertung.getInfizierteProzent("Iran");

        //Assert
        assertThat(infizierte).isCloseTo(0.01, within(0.00005));
    }

    @Test
    public void testFrankreichGetInfizierte(){
        //Act
        double infizierte = auswertung.getInfizierteProzent("Frankreich");

        //Assert
        assertThat(infizierte).isCloseTo(0.0026, within(0.00005));
    }

    @Test
    public void testNulllandGetInfizierte(){
        //Act
        double infizierte = auswertung.getInfizierteProzent("Nullland");

        //Assert
        assertThat(infizierte).isCloseTo(0, within(0.00005));
    }

    @Test
    public void testIrangetTodesrate(){
        //Act
        double tote = auswertung.getTodesrate("Iran");

        //Assert
        assertThat(tote).isCloseTo(3.6,  within(0.05));
    }

    @Test
    public void testFrankreichgetTodesrate(){
        //Act
        double tote = auswertung.getTodesrate("Frankreich");

        //Assert
        assertThat(tote).isCloseTo(1.8,  within(0.05));
    }

    @Test
    public void testNulllandgetTodesrate(){
        //Act
        double tote = auswertung.getTodesrate("Nullland");

        //Assert
        assertThat(tote).isCloseTo(0,  within(0.05));
    }

    @Test
    public void testIrangetText(){
        //Act
        String auswertungsText = auswertung.getText("Iran");

        //Assert
        assertThat(auswertungsText).isEqualTo("Infizierte Prozent:\t0.0100%\nTodesrate:\t3.6%");
    }

    @Test
    public void testFrankreichgetText(){
        //Act
        String auswertungsText = auswertung.getText("Frankreich");

        //Assert
        assertThat(auswertungsText).isEqualTo("Infizierte Prozent:\t0.0026%\nTodesrate:\t1.8%");
    }

    @Test
    public void testNulllandgetText(){
        //Act
        String auswertungsText = auswertung.getText("Nullland");

        //Assert
        assertThat(auswertungsText).isEqualTo("Infizierte Prozent:\t0.0000%\nTodesrate:\t0.0%");
    }

}
