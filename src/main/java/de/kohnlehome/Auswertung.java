package de.kohnlehome;

public class Auswertung implements IAuswertung {
    private ICoronaDaten coronaDaten;
    private IEinwohnerDaten einwohnerDaten;

    public Auswertung(ICoronaDaten coronaDaten, IEinwohnerDaten einwohnerDaten) {
        this.coronaDaten = coronaDaten;
        this.einwohnerDaten = einwohnerDaten;
    }

    @Override
    public double getInfizierteProzent(String land) {
        return (double) coronaDaten.getAnzahlInfiziert(land)/ (double) einwohnerDaten.getAnzahlEinwohner(land)*100.0;
    }

    @Override
    public double getTodesrate(String land) {
        return 0;
    }

    @Override
    public String getText(String land) {
        return null;
    }
}
