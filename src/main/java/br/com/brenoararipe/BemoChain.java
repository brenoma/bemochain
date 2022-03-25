package br.com.brenoararipe;

import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class BemoChain {

    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static final int difficulty = 6;

    public static void main(String[] args) {

        blockchain.add(new Block("This is the first block", "0"));
        blockchain.add(new Block("This is the second block", blockchain.get(blockchain.size()-1).hash));
        blockchain.add(new Block("Hey Im the third block", blockchain.get(blockchain.size()-1).hash));
        blockchain.add(new Block("The last one, i promise", blockchain.get(blockchain.size()-1).hash));
//        System.out.println(blockchain.toString());

        System.out.println("Is Blockchain valid? " + isChainValid());

        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);

        for (int i = 0 ; i < blockchain.size() ; i ++) {
            System.out.println("Mining block " + (i+1) + "...");
            blockchain.get(i).mineBlock(difficulty);
        }

        System.out.println("\n#####__---- The Blockchain: ----__#####\n");
        System.out.println(blockchainJson);
    }

    public static boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;

        for (int i = 1 ; i < blockchain.size() ; i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i-1);

            if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
                System.out.println("Current Hashes not equal");
                return false;
            } else if (!previousBlock.hash.equals(previousBlock.calculateHash())) {
                System.out.println("Previous Hashes not equal");
                return false;
            }
        }
        return true;
    }
}