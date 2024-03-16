package school.sptech.neosspringjava.modal;

public class Local {
    private String number;
    private String datails;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDatails() {
        return datails;
    }

    public void setDatails(String datails) {
        this.datails = datails;
    }

    @Override
    public String toString() {
        return "Local{" +
                "number='" + number + '\'' +
                ", datails='" + datails + '\'' +
                '}';
    }
}
