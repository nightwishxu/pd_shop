package com.api.view.myPawn;
import java.util.List;

import com.paidang.dao.model.PawnAuction;
import com.paidang.daoEx.model.PawnAuctionEx;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 我的典当--详情
 * @author vonkira
 *
 */
@ApiModel
public class AppMyPawnList {

    @ApiModelProperty(value="当品id")
    private Integer id;
    @ApiModelProperty(value="当品名称")
    private String title;
    @ApiModelProperty(value="当品图片")
    private String image;
    @ApiModelProperty(value="鉴定价格")
    private String authPrice;
    @ApiModelProperty(value="出价次数")
    private Integer count;
    @ApiModelProperty(value="状态(0:进行中 1:已结束)")
    private Integer state;
    @ApiModelProperty(value="用户id")
    private Integer userId;
    @ApiModelProperty(value="已贷款额度")
    private String money;
    @ApiModelProperty(value="到期时间")
    private String time;
    @ApiModelProperty(value="我的需求贷款额度")
    private String loansPrice;
    @ApiModelProperty(value="我的需求贷款利率")
    private String loansRate;
    @ApiModelProperty(value="贷款时间")
    private String pawnTime;
    @ApiModelProperty(value="贷款机构列表")
    private List<PawnAuctionEx> pawnAuctionList;
    @ApiModelProperty(value="机构打款凭证")

    private String payeeTicket;
    @ApiModelProperty(value="当票")
    private String pawnTicket;
    @ApiModelProperty(value="0无1已到账")
    private Integer isVerify;
    @ApiModelProperty(value="0未逾期  1逾期")
    private Integer type;
    @ApiModelProperty(value="赎当状态  0没有赎当操作 1赎当处理中 2赎当处理完成")
    private Integer redeemState;
    @ApiModelProperty(value="续当状态 0没有续当操作 1续当处理中 ")
    private Integer continueState;

    @ApiModelProperty(value = "用户 0未生成合同1待签署2已签署3拒签4作废")
    private Integer userStatus;

    @ApiModelProperty(value = "机构 0未生成合同1待签署2已签署3拒签4作废")
    private Integer orgStatus;

    @ApiModelProperty(value = "续当 用户 0未生成合同1待签署2已签署3拒签4作废")
    private Integer continueUserStatus;

    @ApiModelProperty(value = "续当 机构 0未生成合同1待签署2已签署3拒签4作废")
    private Integer continueOrgStatus;

    @ApiModelProperty(value = "项目编号")
    private String projectCode;

    @ApiModelProperty(value = "典当项目编号")
    private String continueProjectCode;

    @ApiModelProperty("合同法链接地址")
    private String hetongfa;

    @ApiModelProperty("民事法链接地址")
    private String minshifa;

    @ApiModelProperty("典当管理法链接地址")
    private String diandangguanlifa;

    @ApiModelProperty("0 未去典当 1已去典当")
    private Integer isGotoPawn;

    @ApiModelProperty("宝贝id")
    private Integer userGoodsId;

    @ApiModelProperty("0 不可以续当 1 可以续当")
    private Integer isContinue;

    @ApiModelProperty(value=" 0 无 1 待完善信息 2待签署 3 待上传凭证 4已完成")
    private Integer pawnStatus;

    @ApiModelProperty(value = "1待签署 2签署合同完成待上传打款凭证 3用户打款并上传凭证4机构确认5机构未收到")
    private Integer continuePawnStatus;

    @ApiModelProperty("最新续当id")
    private Integer lastPawnContinueId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAuthPrice() {
        return authPrice;
    }

    public void setAuthPrice(String authPrice) {
        this.authPrice = authPrice;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLoansPrice() {
        return loansPrice;
    }

    public void setLoansPrice(String loansPrice) {
        this.loansPrice = loansPrice;
    }

    public String getLoansRate() {
        return loansRate;
    }

    public void setLoansRate(String loansRate) {
        this.loansRate = loansRate;
    }

    public String getPawnTime() {
        return pawnTime;
    }

    public List<PawnAuctionEx> getPawnAuctionList() {
        return pawnAuctionList;
    }

    public void setPawnAuctionList(List<PawnAuctionEx> pawnAuctionList) {
        this.pawnAuctionList = pawnAuctionList;
    }

    public void setPawnTime(String pawnTime) {
        this.pawnTime = pawnTime;
    }

    public String getPayeeTicket() {
        return payeeTicket;
    }

    public void setPayeeTicket(String payeeTicket) {
        this.payeeTicket = payeeTicket;
    }

    public Integer getIsVerify() {
        return isVerify;
    }

    public void setIsVerify(Integer isVerify) {
        this.isVerify = isVerify;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getRedeemState() {
        return redeemState;
    }

    public void setRedeemState(Integer redeemState) {
        this.redeemState = redeemState;
    }

    public Integer getContinueState() {
        return continueState;
    }

    public void setContinueState(Integer continueState) {
        this.continueState = continueState;
    }

    public String getPawnTicket() {
        return pawnTicket;
    }

    public void setPawnTicket(String pawnTicket) {
        this.pawnTicket = pawnTicket;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public Integer getOrgStatus() {
        return orgStatus;
    }

    public void setOrgStatus(Integer orgStatus) {
        this.orgStatus = orgStatus;
    }

    public Integer getContinueUserStatus() {
        return continueUserStatus;
    }

    public void setContinueUserStatus(Integer continueUserStatus) {
        this.continueUserStatus = continueUserStatus;
    }

    public Integer getContinueOrgStatus() {
        return continueOrgStatus;
    }

    public void setContinueOrgStatus(Integer continueOrgStatus) {
        this.continueOrgStatus = continueOrgStatus;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getContinueProjectCode() {
        return continueProjectCode;
    }

    public void setContinueProjectCode(String continueProjectCode) {
        this.continueProjectCode = continueProjectCode;
    }

    public String getHetongfa() {
        return hetongfa;
    }

    public void setHetongfa(String hetongfa) {
        this.hetongfa = hetongfa;
    }

    public String getMinshifa() {
        return minshifa;
    }

    public void setMinshifa(String minshifa) {
        this.minshifa = minshifa;
    }

    public String getDiandangguanlifa() {
        return diandangguanlifa;
    }

    public void setDiandangguanlifa(String diandangguanlifa) {
        this.diandangguanlifa = diandangguanlifa;
    }

    public Integer getIsGotoPawn() {
        return isGotoPawn;
    }

    public void setIsGotoPawn(Integer isGotoPawn) {
        this.isGotoPawn = isGotoPawn;
    }

    public Integer getUserGoodsId() {
        return userGoodsId;
    }

    public void setUserGoodsId(Integer userGoodsId) {
        this.userGoodsId = userGoodsId;
    }

    public Integer getIsContinue() {
        return isContinue;
    }

    public void setIsContinue(Integer isContinue) {
        this.isContinue = isContinue;
    }

    public Integer getPawnStatus() {
        return pawnStatus;
    }

    public void setPawnStatus(Integer pawnStatus) {
        this.pawnStatus = pawnStatus;
    }

    public Integer getContinuePawnStatus() {
        return continuePawnStatus;
    }

    public void setContinuePawnStatus(Integer continuePawnStatus) {
        this.continuePawnStatus = continuePawnStatus;
    }


    public Integer getLastPawnContinueId() {
        return lastPawnContinueId;
    }

    public void setLastPawnContinueId(Integer lastPawnContinueId) {
        this.lastPawnContinueId = lastPawnContinueId;
    }
}
