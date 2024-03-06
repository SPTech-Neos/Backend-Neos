package school.sptech.neosspringjava;

public class company {
    private String name;
    private  String fantasyName;
    private  String cnpj;

    private  String Segment;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFantasyName() {
        return fantasyName;
    }

    public void setFantasyName(String fantasyName) {
        this.fantasyName = fantasyName;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getSegment() {
        return Segment;
    }

    public void setSegment(String segment) {
        Segment = segment;
    }

    @Override
    public String toString() {
        return "company{" +
                "name='" + name + '\'' +
                ", fantasyName='" + fantasyName + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", Segment='" + Segment + '\'' +
                '}';
    }
}
