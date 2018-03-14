package net.sf.jxls.transformer;

import org.apache.poi.ss.util.CellRangeAddress;

public class MergedRegionElement implements Comparable<MergedRegionElement> {
	private CellRangeAddress region;

	public MergedRegionElement(CellRangeAddress region){
		this.region = region;
	}
	
	public CellRangeAddress getRegion() {
		return region;
	}

	public void setRegion(CellRangeAddress region) {
		this.region = region;
	}

	@Override
	public int compareTo(MergedRegionElement o) {
		int result = new Integer(this.region.getFirstRow()).compareTo(o.getRegion().getFirstRow());
		if (result != 0) {
			result = new Integer(this.region.getFirstColumn()).compareTo(o.getRegion().getFirstColumn());
		}
		return result;
	}

}
