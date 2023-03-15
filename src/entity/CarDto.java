package entity;

public class CarDto extends Car{
    private String salesmenName;

    public String getSalesmenName() {
        return salesmenName;
    }

    public void setSalesmenName(String salesmenName) {
        this.salesmenName = salesmenName;
    }

    @Override
    public String toString() {
        return "CarDto{" +
                "salesmenName='" + salesmenName + '\'' +
                ", id=" + id +
                ", carName='" + carName + '\'' +
                ", ageLimit=" + ageLimit +
                ", type='" + type + '\'' +
                ", quality='" + quality + '\'' +
                ", price=" + price +
                ", status='" + status + '\'' +
                ", sId=" + sId +
                '}';
    }
}
