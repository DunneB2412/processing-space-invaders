public class Force {
    Vector momentom;
    float rpt, weight;
    Force(Vector momentom, float rpt, float weight){
        this.momentom = momentom;
        this.rpt = rpt;
        this.weight = Math.max(weight,1);
    }
    public void add(Force other){
        this.momentom.add(other.momentom.mul(other.weight/weight));
        this.rpt += other.rpt*(other.weight/weight);
    }

    @Override
    public Force clone(){
        return new Force(momentom.clone(), rpt, weight);
    }
}
