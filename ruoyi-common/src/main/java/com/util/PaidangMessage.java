package com.util;

/**
 * 拍当网消息模板
 */
public enum PaidangMessage {

    /**
     *  4 系：订单信息；更新信息；
     * 1 典：典当成功通知；典当到期通知；赎当通知；赎当邮寄通知；到款通知；当品被竞拍通知；绝当通知；典当到期通知；典当逾期通知；
     * 3 递：商城物流信息；邮寄鉴定物流信息；物品取回信息；
     * 2 鉴：鉴宝信息；
     */

    SYS_NOTIFY(0,1,"系统通知","{}",0,4),
    PAWN_SUCCESS_NOTIFY(0,0,"典当成功通知","您的藏品{}已经被{}成功竞拍得，您的贷款款项将在2小时内到款至您的{}账户!",1,1),
    PAWN_END_NOTIFY(0,0,"典当到期通知","您的藏品{}在{}典当行的典当还款日期即将到期，请您尽快做还款处理！逾期您的藏品将被视作放弃拥有权，即归为{}典当行所有",2,1),
    PAWN_REDEEM_NOTIFY(0,0,"赎当通知","您的藏品{}，在{}典当行的赎当申请已成功受理，将在7个工作日内邮寄至你处",1,1),
    PAWN_REDEEM_POST_NOTIFY(0,1,"赎当邮寄通知","{}",4,1),
    //POST_NOTIFY(0,"邮寄通知","{}",5),
    MONEY_RECEIVE_NOTIFY(0,0,"到款通知","您的账户收到一笔新款项，请前去查看",6,1),
    TRADE_SUCCESS_NOTIFY(0,0,"平台交易信息","平台已打款",10,4),
    AUTH_MAIL_OPER(0,0,"鉴宝信息","您的藏品{}已评估为真品",0,2),
    AUTH_MAIL_OPER_YP(0,0,"鉴宝信息","您的藏品{}已评估为赝品",0,2),
    AUTH_MAIL_OPER_CANNOT_AUTH(0,0,"鉴宝信息","您的藏品{}无法鉴定,理由{}",0,2),
    PAWN_WAS_BEEN_BID(0,0,"当品被竞拍通知","您的藏品被{}典当行竞拍了，请前去查看",8,1),
    PAWN_GOODS_END(0,0,"绝当通知","当品已经绝当",9,1),

    PAWN_END_WARN(0,0,"典当到期通知","您的当品还有{}日即将逾期，请尽快处理",12,1),
    PAWN_END_WARN_LAST(0,0,"典当到期通知","您的当品已逾期，逾期超过5日将变为绝当，请尽快处理",13,1),

    PWAN_OVER_WARN(0,0,"典当逾期通知","你的当品已经逾期{}日，超过5日将变为绝当",14,1),



    //以下是推送至机构端的，即写入p_org_notify表中
    USER_REPAWN_NOTIFY(1,0,"续当通知","当户{}续当成功了，续当时间为{}天，续当费用为{}，请前去查看",1),
    USER_OVERDUE_NOTIFY(1,0,"滞纳通知", "当户{}已经逾期{}天了，马上要变成绝当了，要去跟当户联系了",2),
    ORG_BIDWON_NOFIFY(1,0,"竞拍成功","您竞拍的当品{}，竞拍成功，请尽快支付当款",3),
    USER_REDEEM_NOTIFY(1,0,"赎当通知","当户{}在{}申请赎当，赎当费用为{}",4),
    PAWN_DEAD_NOTIFY(1,0,"绝当通知","你的当品已逾期5日，已成为绝当",5),
    BAOXIANG_DOUDI(1,0,"平台兜底","当品{}已经被您兜底获得，请尽快支付当款",6),
    USER_PAWN_NOTIFY(1,0,"典当通知","当品{}已经发布，快去看看吧",7)
    //USER_EXPIRE_NOTIFY(0,"到期提醒通知","用户{}的当品{}还有{}就要到期了，请注意查看",6),
    ;

    /**]
     * 系统 0 用户端 1 机构端
     */
    private int system;

    /**
     * 消息类型1:系统通知
     */
    private int type;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容模板
     */
    private String template;
    /**
     * 消息跳转类型
     */
    private int redirectType;

    /**
     *  1典   2鉴  3 递  4续
     */
    private int messageType;

    PaidangMessage(int system,int type,String title, String template,int redirectType,int messageType){
        this.system = system;
        this.type = type;
        this.title = title;
        this.template = template;
        this.redirectType = redirectType;
        this.messageType = messageType;
    }

    PaidangMessage(int system,int type,String title, String template,int redirectType){
        this.system = system;
        this.type = type;
        this.title = title;
        this.template = template;
        this.redirectType = redirectType;
    }

    public int getRedirectType() {
        return redirectType;
    }

    public String getTemplate() {
        return template;
    }

    public String getTitle() {
        return title;
    }

    public int getType() {
        return type;
    }

    public int getMessageType() {
        return messageType;
    }

    public int getSystem() {
        return system;
    }


    public static PaidangMessage getByRedirectType(int redirectType,int system) {
        PaidangMessage result = null;
        for (PaidangMessage temp : PaidangMessage.values()) {
            if (temp.getSystem()==system && temp.getRedirectType()==redirectType){
                result = temp;
                break;
            }
        }
        return result;
    }
}

