package entity;
public class OrderDto extends Order{
    private String customerName;
    private String carName;
    private Double cost;
    private String status;
    private String saleDate;
    private String salesmenName;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSalesmenName() {
        return salesmenName;
    }

    public void setSalesmenName(String salesmenName) {
        this.salesmenName = salesmenName;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDate() {
        return saleDate;
    }

    public void setDate(String date) {
        this.saleDate = date;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "customerName='" + customerName + '\'' +
                ", carName='" + carName + '\'' +
                ", cost=" + cost +
                ", status='" + status + '\'' +
                ", date='" + saleDate + '\'' +
                ", salesmenName='" + salesmenName + '\'' +
                ", id=" + id +
                ", cId=" + cId +
                ", sId=" + sId +
                ", saleDate='" + saleDate + '\'' +
                '}';
    }
}