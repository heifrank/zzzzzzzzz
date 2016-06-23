package com.grpc;

import com.grpc.dedup.DedupGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import com.grpc.dedup.CrawlerDedupServer.*;

/**
 * Created by heifrank on 16/5/19.
 */
public class TestGRPC {
    private final ManagedChannel channel;
    private final DedupGrpc.DedupBlockingStub client;

    public TestGRPC(String host, int port) {
        this.channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext(true).build();
        this.client = DedupGrpc.newBlockingStub(channel);
    }

    public void workDedupAndInsert(){
        Document document = Document.newBuilder().setUrl("http://sports.sina.com.cn/others/volleyball/2016-05-15/doc-ifxsenvm0444435.shtml").setTier(3).build();
        Signature signature = Signature.newBuilder().setDoc(document).setSignature("1d9dcad5890adefd").build();
        DedupAndInsertRequest request = DedupAndInsertRequest.newBuilder().setSig(signature).build();
        DedupAndInsertReply reply = client.dedupAndInsert(request);
        System.out.println(reply.toString());
    }

    public void workDedup(){
        DedupRequest request = DedupRequest.newBuilder().setSignature("1d9dcad5890adefd").build();
        DedupReply reply = client.dedup(request);
        System.out.println(reply.toString());
    }

    public static void main(String[] args){
        String host = "10.101.1.194";
        int port = 7321;

        TestGRPC testGRPC = new TestGRPC(host, port);
        testGRPC.workDedup();
    }
}
