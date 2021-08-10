public class CTCICheckSubtree {
    public static void main(String[] args) {
        try{
            CTCICheckSubtree obj = new CTCICheckSubtree();
            obj.run(args);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void run(String[] args){
        //Creates the data(Binary Trees)
        Node node = new Node("1");
        node.leftNode = new Node("8");
        node.rightNode = new Node("8");
        node.rightNode.rightNode = new Node("3");
        node.leftNode.rightNode = new Node("2");
        node.leftNode.leftNode = new Node("9");
        node.rightNode.leftNode = new Node("5");
        //The line below adds a node which makes the result false
        //node.rightNode.rightNode.rightNode = new Node("0");

        Node node2 = new Node("8");
        node2.leftNode = new Node("5");
        node2.rightNode = new Node("3");
        //Prints whether node2 is a subtree of node
        System.out.println(checkForSubtree(node,node2));
    }

    public boolean doesSubtreeExist(Node currNode,Node[] top,Node secondTree,Node secondTreeTop){
        boolean isTop = true;
        if(secondTree == null){
            isTop = false;
        }
        else if(currNode == null || currNode.num != secondTree.num){
            isTop = false;
        }
        if(secondTree != secondTreeTop && !isTop){
            secondTree = secondTreeTop;
            return doesSubtreeExist(currNode,top,secondTree,secondTreeTop);
        }else{
            if(currNode.leftNode != null){
                if(isTop == false){
                    doesSubtreeExist(currNode.leftNode,top,secondTree,secondTreeTop);
                }
                else if(!doesSubtreeExist(currNode.leftNode,top,secondTree.leftNode,secondTreeTop)){
                    isTop = false;
                }
            }else if(secondTree.leftNode != null){
                isTop = false;
                secondTree = secondTreeTop;
            }
            if(currNode.rightNode != null){
                if(isTop == false){
                    doesSubtreeExist(currNode.rightNode,top,secondTree,secondTreeTop);
                }else if(!doesSubtreeExist(currNode.rightNode,top,secondTree.rightNode,secondTreeTop)){
                    isTop = false;
                }
            }else if(secondTree.rightNode != null){
                isTop = false;
                secondTree = secondTreeTop;
            }
            if(isTop){
                top[0] = currNode;
            }
            return isTop;
        }
    }

    public boolean postRunCheck(Node one,Node two){
        if(one == null && two == null){
            return true;
        }else if(one!=null && two!=null){
            boolean checksPassed = true;
            if(!one.num.equals(two.num)){
                checksPassed = false;
            }
            if(!postRunCheck(one.leftNode,two.leftNode)){
                checksPassed = false;
            }
            if(!postRunCheck(one.rightNode,two.rightNode)){
                checksPassed = false;
            }
            return checksPassed;
        }else{
            return false;
        }
    }

    public boolean checkForSubtree(Node one,Node two){
        Node[] topNode = new Node[1];
        doesSubtreeExist(one,topNode,two,two);
        if(topNode[0] != null && postRunCheck(topNode[0],two)){
            return true;
        }
        return false;
    }
}
class Node {
    public String num;
    public Node leftNode;
    public Node rightNode;
    public Node(){}
    public Node(String num){
        this.num = num;
    }
    public Node(String num,Node leftNode,Node rightNode){
        this.num = num;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }
}