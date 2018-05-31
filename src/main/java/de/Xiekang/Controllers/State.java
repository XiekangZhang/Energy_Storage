package main.java.de.Xiekang.Controllers;

public class State<V1, V2> {
    private V1 v1;
    private V2 v2;

    public State(V1 v1, V2 v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    @Override
    public int hashCode() {
        return v1.hashCode() ^ v2.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof State))
            return false;
        State state = (State) o;
        return this.v1.equals(state.getV1()) && this.v2.equals(state.getV2());
    }


    public V1 getV1() {
        return v1;
    }

    public void setV1(V1 v1) {
        this.v1 = v1;
    }

    public V2 getV2() {
        return v2;
    }

    public void setV2(V2 v2) {
        this.v2 = v2;
    }

    @Override
    public String toString() {
        return "(" + this.getV1() + ", " + this.getV2() + ")";
    }
}
