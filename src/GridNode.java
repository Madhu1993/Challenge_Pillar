

import java.util.List;

public class GridNode {
    private int currentGridNodeValue;
    private int upDiagonalAdjColValue;
    private int horizontalAdjColValue;
    private int downDiagonalAdjColValue;

    private GridNode upperDiagonalGridNode;
    private GridNode downDiagonalGridNode;
    private GridNode horizontalGridNode;

    private List<GridNode> childNodes;


    public List<GridNode> getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(List<GridNode> childNodes) {
        this.childNodes = childNodes;
    }

    private int rowNumber;
    private int columnNumber;

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }



    @Override
    public String toString() {
        return "GridNode [currentGridNodeValue=" + currentGridNodeValue + ", upDiagonalAdjColValue="
                + upDiagonalAdjColValue + ", horizontalAdjColValue=" + horizontalAdjColValue
                + ", downDiagonalAdjColValue=" + downDiagonalAdjColValue + ", childNodes=" + childNodes + ", rowNumber="
                + rowNumber + ", columnNumber=" + columnNumber + "]";
    }

    public int getUpDiagonalAdjColValue() {
        return upDiagonalAdjColValue;
    }

    public void setUpDiagonalAdjColValue(int upDiagonalAdjColValue) {
        this.upDiagonalAdjColValue = upDiagonalAdjColValue;
    }

    public int getHorizontalAdjColValue() {
        return horizontalAdjColValue;
    }

    public void setHorizontalAdjColValue(int horizontalAdjColValue) {
        this.horizontalAdjColValue = horizontalAdjColValue;
    }

    public int getDownDiagonalAdjColValue() {
        return downDiagonalAdjColValue;
    }

    public void setDownDiagonalAdjColValue(int downDiagonalAdjColValue) {
        this.downDiagonalAdjColValue = downDiagonalAdjColValue;
    }

    public int getCurrentGridNodeValue() {
        return currentGridNodeValue;
    }

    public void setCurrentGridNodeValue(int currentGridNodeValue) {
        this.currentGridNodeValue = currentGridNodeValue;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + columnNumber;
        result = prime * result + rowNumber;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        GridNode other = (GridNode) obj;
        if (columnNumber != other.columnNumber)
            return false;
        if (rowNumber != other.rowNumber)
            return false;
        return true;
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    public void setColumnNumber(int columnNumber) {
        this.columnNumber = columnNumber;
    }

    public GridNode getUpperDiagonalGridNode() {
        return upperDiagonalGridNode;
    }

    public void setUpperDiagonalGridNode(GridNode upperDiagonalGridNode) {
        this.upperDiagonalGridNode = upperDiagonalGridNode;
    }

    public GridNode getDownDiagonalGridNode() {
        return downDiagonalGridNode;
    }

    public void setDownDiagonalGridNode(GridNode downDiagonalGridNode) {
        this.downDiagonalGridNode = downDiagonalGridNode;
    }

    public GridNode getHorizontalGridNode() {
        return horizontalGridNode;
    }

    public void setHorizontalGridNode(GridNode horizontalGridNode) {
        this.horizontalGridNode = horizontalGridNode;
    }
}
