

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class FindLeastResistancePath {

    public static void main(String[] args) {

        FindLeastResistancePath findLeastResistancePathObject = new FindLeastResistancePath();

        //Reading User's input
        int[][] gridMatrix = findLeastResistancePathObject.readInput();

        //Display Output
        System.out.println(findLeastResistancePathObject.DoesWaterMakeItAllTheWayThroughGrid());
        System.out.println(findLeastResistancePathObject.findTotalMinimumResistancePath(gridMatrix));
        System.out.println(findLeastResistancePathObject.findTheRowsTraversedThroughTheGrid());

    }

    public int[][] readInput(){

        Scanner noOfRowsAndColumns = new Scanner(System.in);
        Scanner input = new Scanner(System.in);
        System.out.println("Enter no.of rows");
        int rows = noOfRowsAndColumns.nextInt();
        System.out.println("Enter no.of columns");
        int columns = noOfRowsAndColumns.nextInt();
        //Creating memory space for entire matrix
        int[][] gridValuesArray = new int[rows][columns];

        try {

            System.out.println("Enter Grid Values as a matrix");
            for (int row = 0; row < rows; row++) {
                for (int column = 0; column < columns; column++) {

                    gridValuesArray[row][column] = input.nextInt();

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            noOfRowsAndColumns.close();
            input.close();
        }
        return gridValuesArray;
    }
    public String DoesWaterMakeItAllTheWayThroughGrid(){
        //TODO:
        return null;
    }
    public int findTotalMinimumResistancePath(int[][] gridMatrix){

        int minResistance = 0;
        int totalResistance = 0;
        GridNode gridNode = null;
        if(gridMatrix.length >=1){

            for(int row=0;row < gridMatrix.length ; row++){
                //for(int column=0;column < gridMatrix[row].length ;column++){

                gridNode = new GridNode();
                gridNode = getCurrentGridNode(row,0,gridMatrix,gridNode);
                //TODO : Calculate the shortest path
                //	System.out.println(gridNode.toString());

                //	}
                totalResistance = getTotalResistance(gridNode);
            }
        }

        return minResistance;
    }
    public int calculateTotalResistance(List<GridNode> gridChildNodes,int totalGridNodeValue,Stack<Integer> stack){

        Map<GridNode,Integer> hashMap = new HashMap<GridNode,Integer>();

        //Total Resistance for all the upperDiagonal paths
        for(GridNode childGridNode : gridChildNodes){
            if(childGridNode != null){

                stack.addElement(childGridNode.getCurrentGridNodeValue());
                totalGridNodeValue = childGridNode.getCurrentGridNodeValue() + totalGridNodeValue ; //10
                if(childGridNode != null &&
                        childGridNode.getChildNodes() != null &&
                        childGridNode.getChildNodes().size() >0){

                    calculateTotalResistance(childGridNode.getChildNodes(), totalGridNodeValue,stack);

                }
                if(childGridNode.getChildNodes().isEmpty()){
                    Map<Stack<Integer>,Integer> totalPathValue = new HashMap<Stack<Integer>,Integer>();
                    totalPathValue.put(stack,totalGridNodeValue);
                    stack.pop();

                    totalGridNodeValue = childGridNode.getCurrentGridNodeValue() + totalGridNodeValue;
                }

            }
        }
        return totalGridNodeValue;

    }
    public int getTotalResistance(GridNode gridNode){
        //TODO
        int currentGridNode = gridNode.getCurrentGridNodeValue();
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(currentGridNode);
        int totalResistance = calculateTotalResistance(gridNode.getChildNodes(), currentGridNode,stack);
		/*Iterator<Integer> it=stack.iterator();
      //  System.out.println("Size before pop() :"+stack.size());

        while(it.hasNext())
        {
            Integer iValue=(Integer)it.next();
            System.out.print("Path :"+iValue.intValue());
        }*/
        return totalResistance;
    }
    public int[] findTheRowsTraversedThroughTheGrid(){
        //TODO :
        return null;
    }

    public GridNode getCurrentGridNode(int currentRow,int currentColumn,int[][] gridMatrix,GridNode currentGridNode){

        int[] path = new int[gridMatrix.length];

        if(currentGridNode != null){

            currentGridNode.setCurrentGridNodeValue(gridMatrix[currentRow][currentColumn]);
            currentGridNode.setRowNumber(currentRow);
            currentGridNode.setColumnNumber(currentColumn);
            currentGridNode.setUpDiagonalAdjColValue(getUpperDiagonalAdjColValue(currentRow,currentColumn,gridMatrix));
            currentGridNode.setDownDiagonalAdjColValue(getDownDiagonalAdjColValue(currentRow,currentColumn,gridMatrix));
            currentGridNode.setHorizontalAdjColValue(getHorizontalAdjColValue(currentRow,currentColumn,gridMatrix));

            List<GridNode> childNodes = new ArrayList<GridNode>();
            GridNode upperDiagonalNode = new GridNode();
            GridNode horizontalDiagonalNode = new GridNode();
            GridNode downDiagonalNode = new GridNode();

            upperDiagonalNode = getUpperDiagonalGridNode(currentRow, currentColumn, gridMatrix,upperDiagonalNode);
            horizontalDiagonalNode = getHorizontalGridNode(currentRow, currentColumn, gridMatrix,horizontalDiagonalNode);
            downDiagonalNode = getDownDiagonalGridNode(currentRow, currentColumn, gridMatrix,downDiagonalNode);

            currentGridNode.setUpperDiagonalGridNode(upperDiagonalNode);
            currentGridNode.setDownDiagonalGridNode(downDiagonalNode);
            currentGridNode.setHorizontalGridNode(horizontalDiagonalNode);

            if(upperDiagonalNode != null) {
                childNodes.add(upperDiagonalNode);
            }
            if(horizontalDiagonalNode != null){
                childNodes.add(horizontalDiagonalNode);
            }
            if(downDiagonalNode != null){
                childNodes.add(downDiagonalNode);
            }
            currentGridNode.setChildNodes(childNodes);
        }
        return currentGridNode;
    }

    public int getHorizontalAdjColValue(int rowNumber,int columnNumber,int[][] gridMatrix){

        int lastColumn =0,nextColumn =0,lastRow=0,previousRow = 0,nextRow = 0,noOfRows =0;

        lastColumn = gridMatrix[rowNumber].length - 1;
        nextColumn = columnNumber +1;
        lastRow = gridMatrix.length -1;
        previousRow = rowNumber-1;
        noOfRows = gridMatrix.length + 1;
        nextRow = rowNumber+1;

        //setVariables(rowNumber,columnNumber,gridMatrix,lastColumn,nextColumn,lastRow,previousRow,nextRow,noOfRows);

        if(columnNumber != lastColumn){
            return gridMatrix[rowNumber][nextColumn];
        }else{
            return 0;
        }
    }

    public int getUpperDiagonalAdjColValue(int rowNumber,int columnNumber,int[][] gridMatrix){

        int lastColumn =0,nextColumn =0,lastRow=0,previousRow = 0,nextRow = 0,noOfRows =0;

        //setVariables(rowNumber,columnNumber,gridMatrix,lastColumn,nextColumn,lastRow,previousRow,nextRow,noOfRows);

        lastColumn = gridMatrix[rowNumber].length - 1;
        nextColumn = columnNumber +1;
        lastRow = gridMatrix.length -1;
        previousRow = rowNumber-1;
        noOfRows = gridMatrix.length + 1;
        nextRow = rowNumber+1;

        if(rowNumber == 0 && noOfRows > 2 && columnNumber != lastColumn){
            return gridMatrix[lastRow][nextColumn];
        }else if(columnNumber == lastColumn){
            return 0;
        }else{
            return gridMatrix[previousRow][nextColumn];
        }
    }

    public int getDownDiagonalAdjColValue(int rowNumber,int columnNumber,int[][] gridMatrix){

        int lastColumn =0,nextColumn =0,lastRow=0,previousRow = 0,nextRow=0,noOfRows =0;

        //setVariables(rowNumber,columnNumber,gridMatrix,lastColumn,nextColumn,lastRow,previousRow,nextRow,noOfRows);

        lastColumn = gridMatrix[rowNumber].length - 1;
        nextColumn = columnNumber +1;
        lastRow = gridMatrix.length -1;
        previousRow = rowNumber-1;
        noOfRows = gridMatrix.length + 1;
        nextRow = rowNumber+1;

        if(rowNumber == lastRow && noOfRows > 2 && columnNumber != lastColumn){
            return	gridMatrix[0][nextColumn];
        }else if(columnNumber == lastColumn){
            return 0;
        }
        else{
            return gridMatrix[nextRow][nextColumn];
        }
    }

    public GridNode getHorizontalGridNode(int rowNumber,int columnNumber,int[][] gridMatrix,GridNode horizontalGridNode){

        int lastColumn =0,nextColumn =0,lastRow=0,previousRow = 0,nextRow=0,noOfRows =0;

        //setVariables(rowNumber,columnNumber,gridMatrix,lastColumn,nextColumn,lastRow,previousRow,nextRow,noOfRows);

        lastColumn = gridMatrix[rowNumber].length - 1;
        nextColumn = columnNumber +1;
        lastRow = gridMatrix.length -1;
        previousRow = rowNumber-1;
        noOfRows = gridMatrix.length + 1;
        nextRow = rowNumber+1;

        if(columnNumber != lastColumn){
            return getCurrentGridNode(rowNumber,nextColumn,gridMatrix,horizontalGridNode);
        }else{
            return null;
        }
    }

    public GridNode getUpperDiagonalGridNode(int rowNumber,int columnNumber,int[][] gridMatrix,GridNode upperDiagonalNode){

        int lastColumn =0,nextColumn =0,lastRow=0,previousRow = 0,nextRow=0,noOfRows =0;

        //setVariables(rowNumber,columnNumber,gridMatrix,lastColumn,nextColumn,lastRow,previousRow,nextRow,noOfRows);

        lastColumn = gridMatrix[rowNumber].length - 1;
        nextColumn = columnNumber +1;
        lastRow = gridMatrix.length -1;
        previousRow = rowNumber-1;
        noOfRows = gridMatrix.length + 1;
        nextRow = rowNumber+1;

        if(rowNumber == 0 && noOfRows > 2 && columnNumber != lastColumn){
            return getCurrentGridNode(lastRow, nextColumn, gridMatrix, upperDiagonalNode);
        }
        else if(columnNumber == lastColumn){
            return null;
        }else{
            return getCurrentGridNode(previousRow,nextColumn,gridMatrix, upperDiagonalNode);
        }
    }

    public GridNode getDownDiagonalGridNode(int rowNumber,int columnNumber,int[][] gridMatrix,GridNode downDiagonalGridNode){

        int lastColumn =0,nextColumn =0,lastRow=0,previousRow = 0,nextRow=0,noOfRows =0;

        //setVariables(rowNumber,columnNumber,gridMatrix,lastColumn,nextColumn,lastRow,previousRow,nextRow,noOfRows);

        lastColumn = gridMatrix[rowNumber].length - 1;
        nextColumn = columnNumber +1;
        lastRow = gridMatrix.length -1;
        previousRow = rowNumber-1;
        noOfRows = gridMatrix.length + 1;
        nextRow = rowNumber+1;

        if(rowNumber ==  lastRow && noOfRows > 2 && columnNumber != lastColumn){
            return	getCurrentGridNode(0,nextColumn,gridMatrix,downDiagonalGridNode);
        }
        else if(columnNumber == lastColumn){
            return null;
        }else{
            return getCurrentGridNode(nextRow,nextColumn,gridMatrix,downDiagonalGridNode);
        }
    }

}
