package entity;

public class CommentDto extends  Comment{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CommentDto{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", oId=" + oId +
                ", content='" + content + '\'' +
                ", sId=" + sId +
                ", commentDate='" + commentDate + '\'' +
                '}';
    }
}
