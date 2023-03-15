package entity;

public class Order {
    /**
    * PK
    */
    protected Integer id;
    /**
    * car id  FK
    */
    protected Integer cId;
    /**
    *  id FK  Staff Customer where user role==0
    */
    protected Integer sId;

    protected String saleDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public Integer getsId() {
        return sId;
    }

    public void setsId(Integer sId) {
        this.sId = sId;
    }

    public String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", cId=").append(cId);
        sb.append(", sId=").append(sId);
        sb.append(", saleDate=").append(saleDate);
        sb.append("]");
        return sb.toString();
    }
}