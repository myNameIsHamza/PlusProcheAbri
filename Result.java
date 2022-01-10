public class Result {
    String boxName;
    String childName;

    public Result(String childName, String boxName ) {
        this.boxName = boxName;
        this.childName = childName;
    }

    public String toString() {
        return childName + "  " + boxName;
    }
}
