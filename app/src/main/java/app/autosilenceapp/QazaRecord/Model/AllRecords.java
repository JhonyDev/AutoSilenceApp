package app.autosilenceapp.QazaRecord.Model;

public class AllRecords {
    private boolean Fajar, duhur, Maghrib, Asar, Isha;
    private String date;

    public AllRecords(String date, boolean fajar, boolean duhur, boolean maghrib, boolean asar, boolean isha) {
        this.date = date;
        Fajar = fajar;
        this.duhur = duhur;
        Maghrib = maghrib;
        Asar = asar;
        Isha = isha;
    }

    public AllRecords() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isFajar() {
        return Fajar;
    }

    public void setFajar(boolean fajar) {
        Fajar = fajar;
    }

    public boolean isDuhur() {
        return duhur;
    }

    public void setDuhur(boolean duhur) {
        this.duhur = duhur;
    }

    public boolean isMaghrib() {
        return Maghrib;
    }

    public void setMaghrib(boolean maghrib) {
        Maghrib = maghrib;
    }

    public boolean isAsar() {
        return Asar;
    }

    public void setAsar(boolean asar) {
        Asar = asar;
    }

    public boolean isIsha() {
        return Isha;
    }

    public void setIsha(boolean isha) {
        Isha = isha;
    }
}
