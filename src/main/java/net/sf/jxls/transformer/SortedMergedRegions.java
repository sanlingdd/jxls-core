package net.sf.jxls.transformer;

import java.util.ArrayList;

import org.apache.poi.ss.util.CellRangeAddress;

public class SortedMergedRegions {

	private ArrayList<MergedRegionElement> regions = new ArrayList<MergedRegionElement>();

	public SortedMergedRegions() {
	}

	public ArrayList<MergedRegionElement> getRegions() {
		return regions;
	}

	public void setRegions(ArrayList<MergedRegionElement> regions) {
		this.regions = regions;
	}

	public void add(MergedRegionElement region) {

		int begin = 0, end = regions.size() - 1;
		while (begin <= end) {
			int middle = (begin + end) / 2;
			MergedRegionElement tempElement = regions.get(middle);
			if (region.compareTo(tempElement) > 0) {
				begin = middle + 1;
			} else {
				end = middle - 1;
			}
		}

		regions.add(begin, region);
		//Collections.sort(regions);
		//regions.toString();
		// regions.add(region);
		// Collections.sort(regions);
	}

	public CellRangeAddress binarySearch(MergedRegionElement element) {
		return this.binarySearch(element.getRegion().getFirstRow(), element.getRegion().getLastColumn());
	}

	public CellRangeAddress binarySearch(int rowNum, int colNum) {
		int begin = 0, end = regions.size() - 1;
		while (begin < end) {
			int middle = (begin + end) / 2;
			MergedRegionElement tempElement = regions.get(middle);
			CellRangeAddress currentRegion = tempElement.getRegion();
			if (rowNum <= currentRegion.getLastRow() && rowNum >= currentRegion.getFirstRow()
					&& colNum >= currentRegion.getFirstColumn() && colNum <= currentRegion.getLastColumn()) {
				return currentRegion;
			} else {
				MergedRegionElement virtual = new MergedRegionElement(
						new CellRangeAddress(rowNum, rowNum, colNum, colNum));
				if (tempElement.compareTo(virtual) > 0) {
					begin = middle + 1;
				} else {
					end = middle - 1;
				}
			}
		}
		return null;
	}

}
