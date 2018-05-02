public class TestMode {
    private Map map;
    private TestDraw helper;
    
    TestMode(Map map) {
        this.map = map;
    }
    
    public void drawMap() { map.draw(); }
}
