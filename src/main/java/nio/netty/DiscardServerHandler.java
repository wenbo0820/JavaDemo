package nio.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * Created by GongWenBo on 2018/1/29.
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
//        ((ByteBuf) msg).release();


        ByteBuf buf = (ByteBuf) msg;
        try {
            while (buf.isReadable()){
                System.out.println((char)buf.readByte());
                System.out.flush();
            }
        }finally {
            ReferenceCountUtil.release(msg);
        }

//        ctx.write(msg);
//        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
