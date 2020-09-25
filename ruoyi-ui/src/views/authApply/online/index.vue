<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="商品名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入商品名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button
          type="cyan"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        >
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
          >重置</el-button
        >
      </el-form-item>
    </el-form>

    <el-table
      v-loading="loading"
      :data="goodsList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="用户账号" align="center" prop="account" />
      <el-table-column label="提交时间" align="center" prop="createTime">
        <template slot-scope="scope">{{
          scope.row.createTime | dateYMDHMSFormat
        }}</template>
      </el-table-column>
      <el-table-column label="用户昵称" align="center" prop="nickName" />
      <el-table-column label="宝贝名称" align="center" prop="name">
        <template slot-scope="scope">
          <el-button
            type="text"
            size="mini"
            v-if="
              scope.row.name == null ||
              scope.row.name == undifined ||
              scope.row.name == ''
            "
            @click="handleForm1(scope.row)"
            >为宝贝命名</el-button
          >
          <el-button
            type="text"
            size="mini"
            v-else
            @click="handleForm1(scope.row)"
            >{{ scope.row.name }}</el-button
          >
        </template>
      </el-table-column>
      <el-table-column label="宝贝类别" align="center" prop="cateName" />

      <el-table-column label="宝贝图片" align="center" prop="images">
        <template slot-scope="scope">
          <div v-if="scope.row.images != null">
            <span
              v-for="(item, index) in scope.row.images.split(',')"
              :key="index"
            >
              <el-popover placement="left" trigger="click" width="300">
                <el-image :src="item" />
                <el-image
                  slot="reference"
                  :src="item"
                  :alt="item"
                  style="max-height: 50px; max-width: 50px; padding: 5px"
                />
              </el-popover>
            </span>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="宝贝附件" align="center" prop="goodsImgs">
        <template slot-scope="scope">
          <div v-if="scope.row.goodsImgs != null">
            <span
              v-for="(item, index) in scope.row.goodsImgs.split(',')"
              :key="index"
            >
              <el-popover placement="left" trigger="click" width="300">
                <el-image :src="item" />
                <el-image
                  slot="reference"
                  :src="item"
                  :alt="item"
                  style="max-height: 50px; max-width: 50px; padding: 5px"
                />
              </el-popover>
            </span>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="宝贝视频" align="center" prop="video">
        <template slot-scope="scope">
          <el-button
            type="primary"
            @click="playVideo(scope.row)"
            ref="btn"
            size="mini"
          >
            播 放
          </el-button>
        </template>
      </el-table-column>
      <el-table-column
        label="估价"
        align="center"
        prop="PriceTest"
      ></el-table-column>
      <el-table-column
        label="鉴定说明"
        align="center"
        prop="appraisalDsc"
      ></el-table-column>
      <el-table-column
        label="专家鉴定"
        align="center"
        prop="auth"
      ></el-table-column>
      <el-table-column
        label
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['admin:goods:edit']"
            >查看详情</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <el-dialog
      :title="title1"
      :visible.sync="open1"
      width="400px"
      append-to-body
    >
      <el-form ref="form1" :model="form1" label-width="150px">
        <el-form-item label="设置宝贝名称" prop="settleMoney" id="refuseInfo">
          <el-input v-model="form1.name" placeholder="请输名称" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="from1Submit">确 定</el-button>
        <el-button @click="cancel1">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 添加或修改商品对话框 -->
    <el-dialog
      :title="title"
      :visible.sync="open"
      width="800px"
      append-to-body
      v-if="isRouterAlive"
    >
      <el-form ref="form" :model="form" label-width="150px">
        <el-form-item label="上传的寄卖图片" prop="sellImgs">
          <!-- <el-image :src="form.sellImgs"></el-image> -->
          <template slot-scope="scope">
            <span
              v-for="(item, index) in form.sellImgs.split(',')"
              :key="index"
            >
              <el-popover placement="left" trigger="click" width="300">
                <el-image :src="item" />
                <el-image
                  slot="reference"
                  :src="item"
                  :alt="item"
                  style="max-height: 50px; max-width: 50px; padding: 5px"
                />
              </el-popover>
            </span>
          </template>
        </el-form-item>
        <el-form-item label="上传的寄卖视频" prop="sellVideo">
          <!-- <video class="avatar video-avatar" controls="controls" :src="form.sellVideo"></video> -->
          <template slot-scope="scope">
            <!-- <video
              v-if="form.sellVideo!=''"
              　　:src="form.sellVideo"
              　　class="avatarvideo-avatar"
              　　controls="controls"
            >您的浏览器不支持视频播放</video>-->
            <video
              v-if="form.sellVideo"
              class="avatar"
              preload="auto"
              controls="controls"
              :src="form.sellVideo"
            ></video>
          </template>
        </el-form-item>
        <el-form-item label="寄卖商品描述" prop="sellInfo">
          <el-input v-model="form.sellInfo" disabled></el-input>
        </el-form-item>
        <el-form-item label="一口价(元)" prop="sellPrice">
          <el-input v-model="form.sellPrice" disabled></el-input>
        </el-form-item>
        <el-form-item label="鉴定价(元)" prop="authPrice">
          <el-input v-model="form.authPrice" disabled></el-input>
        </el-form-item>
        <el-form-item label="邮寄鉴定结果" prop="authResult">
          <el-input
            v-model="form.authResult"
            style="color: red"
            disabled
          ></el-input>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import {
  goodsList,
  getGoods,
  delGoods,
  addGoods,
  updateGoods,
  settle,
  changeCheck,
  exportGoods,
} from "@/api/postStore/goods";
import SingleUpload from "@/components/Upload/singleUpload";
import VideoUpload from "@/components/Upload/videoUpload";
import MultiUpload from "@/components/Upload/multiUpload";

export default {
  name: "Goods",
  components: { SingleUpload, MultiUpload, VideoUpload },
  //  props: {
  //     form: {}
  // },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 商品表格数据
      goodsList: [],

      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      open1: false,
      open2: false,
      title1: "",

      isRouterAlive: true,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        source: null,
        type: null,
        cateCode: null,
        orgId: null,
        goodsOwner: null,
        goodsId: null,
        name: null,
        cost: null,
        price: null,
        total: null,
        soldOut: null,
        isOnline: null,
        isVerfiy: null,
        imgs: null,
        img: null,
        width: null,
        height: null,
        type: null,
        info: null,
        modifyTime: null,
        state: null,
        maxAutionId: null,
        maxAuction: null,
        userId: null,
        platformRate: null,
        platformMoney: null,
        platformState: null,
        sortOrder: null,
        isSuggest: null,
        refuseInfo: null,
        spec: null,
        ccLength: null,
        ccWidth: null,
        ccHeight: null,
        weight: null,
        material: null,
        createYear: null,
        bannerVideo: null,
        bannerVideoFace: null,
        v: null,
      },
      // 表单参数
      form: {},
      form1: {},
      uploadAction: process.env.BASE_API + "common/fileUplaod",
      // imgfileList:[],
      // 表单校验
      rules: {
        source: [
          {
            required: true,
            message: "1平台2机构3服务商4供应商5寄拍不能为空",
            trigger: "blur",
          },
        ],
        cateCode: [
          { required: true, message: "分类code不能为空", trigger: "blur" },
        ],
        name: [
          { required: true, message: "商品名称不能为空", trigger: "blur" },
        ],
        total: [{ required: true, message: "库存不能为空", trigger: "blur" }],
        soldOut: [{ required: true, message: "已售不能为空", trigger: "blur" }],
        isOnline: [
          { required: true, message: "1上架0下架不能为空", trigger: "blur" },
        ],
        isVerfiy: [
          {
            required: true,
            message: "1审核中2通过3不通过不能为空",
            trigger: "blur",
          },
        ],
        createTime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" },
        ],
      },
      cateCodeOptions: [
        {
          label: "钟表",
          value: 1,
        },
        {
          label: "翡翠",
          value: 2,
        },
        {
          label: "和田玉",
          value: 3,
        },
        {
          label: "古董艺术",
          value: 4,
        },
        {
          label: "书画",
          value: 5,
        },
        {
          label: "彩色珠宝",
          value: 6,
        },
        {
          label: "钻石",
          value: 7,
        },
        {
          label: "其他",
          value: 8,
        },
      ],
      cateCodeSonOptions: [],
      cateCodeSonOptions1: [
        {
          label: "明清砚台",
          value: 9,
        },
        {
          label: "文玩",
          value: 10,
        },
        {
          label: "杂项",
          value: 11,
        },
      ],
      cateCodeSonOptions2: [
        {
          label: "红蓝宝石",
          value: 12,
        },
        {
          label: "祖母绿",
          value: 13,
        },
        {
          label: "珍珠",
          value: 14,
        },
        {
          label: "碧玺",
          value: 15,
        },
      ],
    };
  },
  computed: {
    imgfileList: {
      get: function () {
        let pics = [];
        // if(this.form.img===undefined||this.form.img==null||this.form.img===''){
        //   return pics;
        // }
        // pics.push(this.value.img);
        if (
          this.form.imgs === undefined ||
          this.form.imgs == null ||
          this.form.imgs === ""
        ) {
          return pics;
        }
        let albumPics = this.form.imgs.split(",");
        for (let i = 0; i < albumPics.length; i++) {
          pics.push(albumPics[i]);
        }
        return pics;
      },
      set: function (newValue) {
        if (newValue == null || newValue.length === 0) {
          // this.form.img = null;
          this.form.imgs = null;
        } else {
          // this.form.img = newValue[0];
          console.info("type = " + Object.prototype.toString.call(newValue));
          //  var values = newValue.split(",");
          //  if(values==null || values.length===0){
          //     this.form.imgs = null;
          //  }
          console.info("imgs start " + this.form.imgs);
          console.info("imgs newValue " + newValue);

          this.form.imgs = "";
          if (newValue.length > 1) {
            for (let i = 0; i < newValue.length; i++) {
              this.form.imgs += newValue[i];
              if (i !== newValue.length - 1) {
                this.form.imgs += ",";
              }
            }
          } else {
            this.form.imgs = newValue[0];
          }
          console.info("imgs " + this.form.imgs);
        }
      },
    },
  },
  created() {
    this.getList();
  },
  methods: {
    reload() {
      console.info("reload");
      this.isRouterAlive = false;
      this.$nextTick(() => (this.isRouterAlive = true));
    },
    handleSellStatus(row, column) {
      if (row.sellStatus === 0) {
        return "未上架";
      } else if (row.sellStatus === 1) {
        return "上架";
      } else if (row.sellStatus === 2) {
        return "已卖出";
      }
    },
    handleForm1(row) {
      this.reset1();
      const id = row.id;
      this.form1 = { id: id, name: row.name };
      this.open1 = true;
      this.title1 = "宝贝名称";
    },
    handleIsSuggest(row) {
      var data = {};
      if (row.isSuggest == 1) {
        data = { id: row.id, isSuggest: 0 };
      } else {
        data = { id: row.id, isSuggest: 1 };
      }
      updateGoods(data).then((response) => {
        if (response.code === 200) {
          this.msgSuccess("修改成功");
          this.getList();
        }
      });
    },
    from1Submit() {
      updateGoods(this.form1).then((response) => {
        if (response.code === 200) {
          this.msgSuccess("修改成功");
          this.open1 = false;
          this.getList();
        }
      });
    },
    changeCheckBtn(row) {
      var data = { id: row.id, v: row.sellCheck };
      console.info(data);
      changeCheck(data).then((response) => {
        if (response.code === 200) {
          this.msgSuccess("修改成功");
          this.getList();
        }
      });
    },
    handleOrderState(row, column) {
      if (row.orderState === 1) {
        return "待付款";
      } else if (row.orderState === 2) {
        return "已付款";
      } else if (row.orderState === 3) {
        return "已发货";
      } else if (row.orderState === 4) {
        return "确认收货";
      } else if (row.orderState === 5) {
        return "已评价";
      }
    },
    handleSettleStatus(row, column) {
      if (row.settleStatus === 2) {
        return "已结算";
      } else {
        return "未结算";
      }
    },
    handleCateCode(row, column) {
      if (row.cateCode === 1) {
        return "钟表";
      } else if (row.cateCode === 2) {
        return "翡翠";
      } else if (row.cateCode === 3) {
        return "和田玉";
      } else if (row.cateCode === 4) {
        return "古董艺术";
      } else if (row.cateCode === 5) {
        return "书画";
      } else if (row.cateCode === 6) {
        return "彩色珠宝";
      } else if (row.cateCode === 7) {
        return "钻石";
      } else if (row.cateCode === 8) {
        return "其他";
      } else if (row.cateCode === 9) {
        return "明清砚台";
      } else if (row.cateCode === 10) {
        return "文玩";
      } else if (row.cateCode === 11) {
        return "杂项";
      } else if (row.cateCode === 12) {
        return "红蓝宝石";
      } else if (row.cateCode === 13) {
        return "祖母绿";
      } else if (row.cateCode === 14) {
        return "珍珠";
      } else if (row.cateCode === 15) {
        return "碧玺";
      } else {
        return "--";
      }
    },
    selectCateCode(value) {
      if (value != null && value === 4) {
        this.cateCodeSonOptions = [
          {
            label: "明清砚台",
            value: "9",
          },
          {
            label: "文玩",
            value: "10",
          },
          {
            label: "杂项",
            value: "11",
          },
        ];
      } else if (value != null && value === 6) {
        this.cateCodeSonOptions = [
          {
            label: "红蓝宝石",
            value: "12",
          },
          {
            label: "祖母绿",
            value: "13",
          },
          {
            label: "珍珠",
            value: "14",
          },
          {
            label: "碧玺",
            value: "15",
          },
        ];
      } else {
        this.cateCodeSonOptions = [];
        // this.form.cateCodeSon = '';
        this.$set(this.form, "cateCodeSon", "");
      }
    },
    getImgs() {
      return [];
    },
    /** 查询商品列表 */
    getList() {
      this.loading = true;
      this.queryParams.type = "onLine";
      goodsList(this.queryParams).then((response) => {
        this.goodsList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    cancel1() {
      this.open1 = false;
      this.reset1();
    },
    cancel2() {
      this.open2 = false;
      this.reset2();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        sellImgs: null,
        sellVideo: null,
        sellInfo: null,
        sellPrice: null,
        authPrice: null,
        authResult: null,
      };
      this.resetForm("form");
      this.reload();
      // this.$refs.upload.clearFiles()
      setTimeout(() => {
        // this.$refs.upload.clearFiles()
      }, 1);
    },
    reset1() {
      this.form1 = {
        id: null,
        name: null,
      };
      this.resetForm("form1");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map((item) => item.id);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      // this.$refs.upload.clearFiles()
      this.open = true;
      this.title = "添加商品";
      // setTimeout(() => {
      //   // console.info("ref "+ JSON.stringify(this.$refs.upload))
      //   this.$refs.upload.clearFiles()
      // }, 10);
    },
    handleOperate(row) {},
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      // console.info("img: "+this.imgfileList)
      //       this.imgfileList.get = [];

      const id = row.id || this.ids;
      getGoods(id).then((response) => {
        this.reset();
        var authResult = response.data.authResult;
        var res;
        switch (authResult) {
          case -1:
            res = "无";
            break;
          case 0:
            res = "未鉴定";
            break;
          case 1:
            res = "鉴定中";
            break;
          case 2:
            res = "无法鉴定";
            break;
          case 3:
            res = "赝品";
            break;
          case 4:
            res = "真品";
            break;
        }
        response.data.authResult = res;
        this.form = response.data;
        this.form.sellImgs =
          "https://app-cdn.starcharge.com/332E9212-D70D-4345-BC47-1EDB6EF5C612-iOS.jpg,https://app-cdn.starcharge.com/332E9212-D70D-4345-BC47-1EDB6EF5C612-iOS.jpg";
        this.form.sellVideo =
          "http://localhost:8080/download?id=88c54ec55ba4492eaadfa3c46c2fd3df";
        this.open = true;
        this.title = "详情";
      });
    },

    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          this.form.source = 3;
          this.form.type = 1;
          // this.form.imgs = this.imgfileList.join(",")

          if (this.form.id != null) {
            updateGoods(this.form).then((response) => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addGoods(this.form).then((response) => {
              if (response.code === 200) {
                this.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              }
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除商品编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return delGoods(ids);
        })
        .then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
        .catch(function () {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm("是否确认导出所有商品数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return exportGoods(queryParams);
        })
        .then((response) => {
          this.download(response.msg);
        })
        .catch(function () {});
    },
  },
};
</script>
