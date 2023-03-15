package entity;

public class StaffDto extends Staff{
    private String userRole;

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "StaffDto{" +
                "UserRole='" + userRole + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", contactNumber='" + contactNumber + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", icNumber='" + icNumber + '\'' +
                ", uid=" + uid +
                '}';
    }
}
