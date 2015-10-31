
public class DataType {

	public int temp;
	public int humid;
	public int light;
	public long seqid;
	public long time;
	public long counter;
	public int node2_retrans;
        public int node1_overflow;
        public int node0_retrans;

	public DataType(int temp, int humid, int light, long seqid, long time, int node2_retrans, int node1_overflow, int node0_retrans) {
		this.temp = temp;
		this.humid = humid;
		this.light = light;
		this.seqid = seqid;
		this.time = time;
		this.node2_retrans = node2_retrans;
		this.node1_overflow = node1_overflow;
		this.node0_retrans = node0_retrans; 
		this.counter = seqid;
	}
	
	public double getPhysicalTemp() {
		int SOT = this.temp & 16383;
		return (-39.6 + 0.01 * SOT);
	}
	
	public double getPhysicalHumid() {
		int SORH = this.humid & 4095;
		double temp = this.getPhysicalTemp();
		double linear = -4 + 0.0405 * SORH - 2.8e-6 * SORH * SORH;
		return ((temp - 25) * (0.01 + 0.00008 * SORH) + linear);
	}
	
	public double getPhysicalLight() {
		return (0.085 * this.light);
	}

	public double getRetransRateDueToLink() {
		//return double(this.node2_retrans - this.node1_overflow)/(this.counter + this.node2_retrans);
		return this.node2_retrans;
	} 

	public double getRetransRateDueToOverflow(){
		//return double(this.node1_overflow)/(this.counter + this.node2_retrans);
		return this.node0_retrans;
	}
}
