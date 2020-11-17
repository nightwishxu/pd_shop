<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item
        label="商品名称"
        prop="name"
      >
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
        >搜索</el-button>
        <el-button
          icon="el-icon-refresh"
          size="mini"
          @click="resetQuery"
        >重置</el-button>
      </el-form-item>
    </el-form>

    <el-row
      :gutter="10"
      class="mb8"
    >
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['admin:goods:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['admin:goods:edit']"
        >修改</el-button>
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['admin:goods:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['admin:goods:export']"
        >导出</el-button>
      </el-col>-->
      <div class="top-right-btn">
        <el-tooltip
          class="item"
          effect="dark"
          content="刷新"
          placement="top"
        >
          <el-button
            size="mini"
            circle
            icon="el-icon-refresh"
            @click="handleQuery"
          />
        </el-tooltip>
        <el-tooltip
          class="item"
          effect="dark"
          :content="showSearch ? '隐藏搜索' : '显示搜索'"
          placement="top"
        >
          <el-button
            size="mini"
            circle
            icon="el-icon-search"
            @click="showSearch = !showSearch"
          />
        </el-tooltip>
      </div>
    </el-row>

    <el-table
      v-loading="loading"
      :data="goodsList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column
        type="selection"
        width="55"
        align="center"
      />
      <el-table-column
        label="分类"
        align="center"
        prop="cateCode"
        :formatter="handleCateCode"
      />
      <el-table-column
        label="商品名称"
        align="center"
        prop="name"
      />
      <!-- <el-table-column columnlabel="商品封面" align="center" prop="img">
        <template scope="scope">
          <el-popover placement="right" title trigger="click">
            <el-image
              slot="reference"
              :src="scope.row.img"
              :alt="scope.row.img"
              style="max-height: 50px; max-width: 50px"
            ></el-image>
            <el-image :src="scope.row.img"></el-image>
          </el-popover>
        </template> -->
      </el-table-column>
      <el-table-column
        label="图片"
        align="center"
        prop="imgs"
      >
        <template scope="scope">
          <span
            v-for="(item, index) in scope.row.imgs.split(',')"
            :key="index"
          >
            <el-popover
              placement="left"
              trigger="click"
              width="300"
            >
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
      </el-table-column>
      <el-table-column
        label="商品来源"
        align="center"
        prop="orgName"
      />
      <el-table-column
        label="售价"
        align="center"
        prop="price"
      />
      <el-table-column
        label="库存"
        align="center"
        prop="total"
      />
      <el-table-column
        label="已售"
        align="center"
        prop="soldOut"
      />
      <!-- <el-table-column label="状态" align="center" prop="isOnline" :formatter="handleIsOnline" /> -->
      <!-- <el-table-column label="审核" align="center">
        <template slot-scope="scope">
          <el-button
            type="text"
            size="mini"
            v-if="scope.row.isVerfiy === 1"
            @click="handleIsVerify(scope.row)"
            >开始审核</el-button
          >
          <el-button
            type="text"
            size="mini"
            v-if="scope.row.isVerfiy === 2"
            @click="handleIsVerify(scope.row)"
            >审核通过</el-button
          >
          <el-button
            type="text"
            size="mini"
            v-if="scope.row.isVerfiy === 3"
            @click="handleIsVerify(scope.row)"
            >审核不通过</el-button
          >
        </template>
      </el-table-column> -->
      <!-- <el-table-column label="排序(倒序)" align="center" prop="sortOrder" /> -->
      <!-- <el-table-column label="是否设置为推荐" align="center" prop="isSuggest">
        <template slot-scope="scope">
          <el-button
            type="text"
            size="mini"
            v-if="scope.row.isSuggest === 1"
            @click="handleIsSuggest(scope.row)"
            >取消首页推荐</el-button
          >
          <el-button
            type="text"
            size="mini"
            v-else
            @click="handleIsSuggest(scope.row)"
            >设置为首页推荐</el-button
          >
        </template>
      </el-table-column>
      <el-table-column
        label="审核不通过原因"
        align="center"
        prop="refuseInfo"
      /> -->
      <!-- <el-table-column label="排序(倒序)" align="center" prop="sortOrder" /> -->
      <el-table-column
        label="创建时间"
        align="center"
        prop="createTime"
        width="180"
      >
        <template slot-scope="scope">{{
          scope.row.createTime | dateYMDHMSFormat
        }}</template>
      </el-table-column>
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['admin:goods:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['admin:goods:remove']"
          >删除</el-button>
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
      <el-form
        ref="form1"
        :model="form1"
        label-width="150px"
      >
        <el-form-item
          label="原因"
          prop="refuseInfo"
          id="refuseInfo"
        >
          <el-input
            v-model="form1.refuseInfo"
            placeholder="请输入原因"
          />
        </el-form-item>
      </el-form>
      <div
        slot="footer"
        class="dialog-footer"
      >
        <el-button
          type="primary"
          @click="changeStateBtn"
        >确 定</el-button>
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
      <el-form
        ref="form"
        :model="form"
        :rules="rules"
        label-width="120px"
      >
        <el-row>
          <el-col :span="8">
            <el-form-item
              label="商品分组"
              prop="cateCode"
            >
              <el-select
                v-model="form.cateCode"
                placeholder="请选择下拉选择"
                @change="selectCateCode"
                clearable
                :style="{ width: '100%' }"
              >
                <el-option
                  v-for="item in cateCodeOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                  :disabled="item.disabled"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <!-- <el-col :span="8">
            <el-form-item label="子分类" prop="cateCodeSoncateCodeSon">
              <el-select
                ref="cateCodeSon"
                v-model="form.cateCodeSon"
                placeholder="请选择下拉选择"
                clearable
                :style="{ width: '100%' }"
              >
                <el-option
                  v-for="item in cateCodeSonOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col> -->
        </el-row>
        <el-form-item
          label="商品名称"
          prop="name"
        >
          <el-input
            v-model="form.name"
            placeholder="请输入商品名称"
          />
        </el-form-item>
        <el-form-item
          label="商品描述"
          prop="introduction"
        >
          <el-input
            v-model="form.introduction"
            placeholder="请输入商品描述"
          />
        </el-form-item>
        <el-form-item
          label="库存"
          prop="total"
        >
          <el-input
            v-model="form.total"
            placeholder="请输入库存"
          />
        </el-form-item>
        <el-form-item
          label="商品封面"
          prop="img"
        >
          <single-upload
            v-model="form.img"
            style="width: 300px; display: inline-block; margin-left: 10px"
          ></single-upload>
        </el-form-item>
        <el-form-item label="组图">
          <!-- <el-input v-model="form.imgs" placeholder="请输入图片" /> -->
          <multi-upload
            ref="upload"
            v-model="imgfileList"
          ></multi-upload>
        </el-form-item>

        <el-form-item
          label="排序(倒序)"
          prop="sortOrder"
        >
          <el-input
            v-model="form.sortOrder"
            placeholder="请输入排序(倒序)"
          />
        </el-form-item>
        <!-- <el-form-item label="轮播图视频封面" prop="bannerVideoFace">
          <single-upload
            v-model="form.bannerVideoFace"
            style="width: 300px; display: inline-block; margin-left: 10px"
          ></single-upload>
        </el-form-item>
        <el-form-item label="轮播图视频" prop="bannerVideo">
          <video-upload
            v-model="form.bannerVideo"
            style="width: 300px; display: inline-block; margin-left: 10px"
          ></video-upload>
        </el-form-item>
        <el-form-item label="封面宽度" prop="width">
          <el-input v-model="form.width" placeholder="请输入封面宽度" />
        </el-form-item>
        <el-form-item label="封面高度" prop="height">
          <el-input v-model="form.height" placeholder="请输入封面高度" />
        </el-form-item>
        <el-form-item label="商品描述" prop="info">
          <el-input v-model="form.info" placeholder="请输入商品描述" />
        </el-form-item>

      

        <el-form-item label="规格" prop="spec">
          <el-input v-model="form.spec" placeholder="请输入规格" />
        </el-form-item>
        <el-form-item label="尺寸--长" prop="ccLength">
          <el-input v-model="form.ccLength" placeholder="请输入尺寸--长" />
        </el-form-item>
        <el-form-item label="尺寸--宽" prop="ccWidth">
          <el-input v-model="form.ccWidth" placeholder="请输入尺寸--宽" />
        </el-form-item>
        <el-form-item label="尺寸--高" prop="ccHeight">
          <el-input v-model="form.ccHeight" placeholder="请输入尺寸--高" />
        </el-form-item>
        <el-form-item label="重量" prop="weight">
          <el-input v-model="form.weight" placeholder="请输入重量" />
        </el-form-item>
        <el-form-item label="材质" prop="material">
          <el-input v-model="form.material" placeholder="请输入材质" />
        </el-form-item> -->

        <draggable
          :list="attributeOptions"
          :animation="340"
          group="selectItem"
          handle=".option-drag"
        >
          <div
            v-for="(item, index) in attributeOptions"
            :key="index"
          >
            <!-- <div class="select-line-icon option-drag">
                <i class="el-icon-s-operation" />
              </div> -->
            <!-- <el-input v-model="item.label" placeholder="选项名" size="small" :style="{ width: '40%' }"/> -->
            <el-row>
              <el-col :span="8">
                <el-form-item>
                  <el-select
                    v-model="item.label"
                    placeholder="选项名"
                    clearable
                    :style="{ width: '100%' }"
                  >
                    <el-option
                      v-for="item in attributeKeyOptions"
                      :key="item.name"
                      :label="item.name"
                      :value="item.name"
                      :disabled="item.disabled"
                    ></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-input
                  placeholder="选项值"
                  :value="item.value"
                  :style="{ width: '100%' }"
                  @input="setOptionValue(item, $event)"
                />
              </el-col>
              <el-col :span="4">
                <div
                  class="close-btn select-line-icon"
                  @click="attributeOptions.splice(index, 1)"
                >
                  <i class="el-icon-remove-outline" />
                </div>
              </el-col>
            </el-row>

          </div>
        </draggable>
        <div style="margin-left: 20px;">
          <el-button
            style="padding-bottom: 0"
            icon="el-icon-circle-plus-outline"
            type="text"
            @click="addSelectItem"
          >
            添加属性
          </el-button>
        </div>
        <el-divider />

        <el-form-item
          label="成交方式"
          prop="dealType"
        >
          <el-select
            v-model="form.dealType"
            placeholder="请选择成交方式"
            clearable
            @change="selectDealType"
            :style="{ width: '100%' }"
          >
            <el-option
              v-for="item in dealTypeOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
              :disabled="item.disabled"
            ></el-option>
          </el-select>
        </el-form-item>

        <el-form-item
          label="售价"
          prop="price"
          v-if="dealType1"
        >
          <el-input
            v-model="form.price"
            placeholder="请输入售价"
          />
        </el-form-item>

        <el-form-item
          label="上架方式"
          prop="isOnline"
          v-if="dealType1"
        >
          <el-radio-group
            v-model="form.isOnline"
            size="medium"
            @change="selectIsOnline"
          >
            <el-radio
              v-for="(item, index) in onlineOptions"
              :key="index"
              :label="item.value"
              @change="selectIsOnline"
              :disabled="item.disabled"
            >{{item.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item
          label="上架时间"
          prop="onlineTime"
          v-if="onLineTimeFlag"
        >
          <el-date-picker
            v-model="form.onlineTime"
            type="datetime"
            format="yyyy-MM-dd HH:mm:ss"
            value-format="yyyy-MM-dd HH:mm:ss"
            :style="{width: '100%'}"
            placeholder="请选择日期选择"
            clearable
          ></el-date-picker>
        </el-form-item>

        <el-form-item
          label="起拍时间"
          prop="auctionStartTime"
          v-if="dealType2"
        >
          <el-date-picker
            v-model="form.auctionStartTime"
            type="datetime"
            format="yyyy-MM-dd HH:mm:ss"
            value-format="yyyy-MM-dd HH:mm:ss"
            :style="{width: '100%'}"
            placeholder="请选择日期选择"
            clearable
          ></el-date-picker>
        </el-form-item>

        <el-form-item
          label="截止时间"
          prop="auctionEndTime"
          v-if="dealType2"
        >
          <el-date-picker
            v-model="form.auctionEndTime"
            type="datetime"
            format="yyyy-MM-dd HH:mm:ss"
            value-format="yyyy-MM-dd HH:mm:ss"
            :style="{width: '100%'}"
            placeholder="请选择日期选择"
            clearable
          ></el-date-picker>
        </el-form-item>

        <el-form-item
          label="起拍价"
          prop="startPrice"
          v-if="dealType2"
        >
          <el-input
            v-model="form.startPrice"
            placeholder="请输入起拍价"
          />
        </el-form-item>

        <el-form-item
          label="加价幅度"
          prop="raisePriceRange"
          v-if="dealType2"
        >
          <el-input
            v-model="form.raisePriceRange"
            placeholder="请输入加价幅度"
          />
        </el-form-item>

        <el-form-item
          label="标签"
          prop="labels"
        >
          <el-checkbox-group
            v-model="form.labels"
            size="medium"
          >
            <el-checkbox
              v-for="(item, index) in labelOptions"
              :key="index"
              :label="item.value"
              :disabled="item.disabled"
            >{{item.label}}</el-checkbox>
          </el-checkbox-group>
        </el-form-item>

        <!-- <el-form-item label="标签">
          <div style="margin-left: 20px;">
            <el-input
              ref="label"
              v-model="labelValue"
              placeholder="标签"
              size="small"
              :style="{ width: '40%' }"
            />
            <el-button
              id="btn"
              @click="addLabels"
            >添加商品标签</el-button>
          </div>
          <draggable
            :list="attributeOptions"
            :animation="340"
            group="selectItem"
            handle=".option-drag"
          >
            <div
              v-for="(item, index) in labelsOptions"
              :key="index"
            >
              <div
                class="close-btn select-line-icon"
                @click="labelsOptions.splice(index, 1)"
              >
                {{item}}
                <i class="el-icon-remove-outline" />
              </div>

            </div>
          </draggable>

        </el-form-item> -->
      </el-form>
      <div
        slot="footer"
        class="dialog-footer"
      >
        <el-button
          type="primary"
          @click="submitForm"
        >确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listGoods,
  getGoods,
  delGoods,
  addGoods,
  updateGoods,
  changeState,
  exportGoods,
  getAttribute,
} from "@/api/admin/goods";
import SingleUpload from "@/components/Upload/singleUpload";
import VideoUpload from "@/components/Upload/videoUpload";
import MultiUpload from "@/components/Upload/multiUpload";
import { isNumberStr } from '@/utils/index';


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
      dealType1:false,
      dealType2:false,
      onLineTimeFlag:false,
      labelValue:"",
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
        introduction: null,
        goodsAttribute: null,
        dealType: null,
        onlineTime: null,
        auctionStartTime: null,
        auctionEndTime: null,
        startPrice: null,
        raisePriceRange: null,

      },
      // 表单参数
      form: {
        labels:[],
      },
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
      attributeOptions:[],
      labelsOptions:[],
      attributeKeyOptions:[],
      cateCodeOptions: [
        {
          label: "玉翠珠宝",
          value: 101,
        },
        {
          label: "工艺作品",
          value: 102,
        },
        {
          label: "文玩杂项",
          value: 103,
        },
        {
          label: "紫砂陶瓷",
          value: 104,
        },
        {
          label: "钱币邮票",
          value: 105,
        },
        {
          label: "书画篆刻",
          value: 106,
        },
        {
          label: "花鸟文娱",
          value: 107,
        },
        {
          label: "其他",
          value: 108,
        },
      ],
      onlineOptions: [{
        "label": "立即上架",
        "value": 1
      }, {
        "label": "定时上架",
        "value": 3
      }, {
        "label": "暂不上架，放入仓库",
        "value": 2
      }],
      dealTypeOptions:[
        {
          label:"一口价",
          value:1
        },
        {
          label:"拍卖",
          value:2
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
      // ：包邮，假必赔，可鉴定，包退，已鉴定
      labelOptions: [{
        "label": "包邮",
        "value": "1"
      }, {
        "label": "假必赔",
        "value": "2"
      }, {
        "label": "可鉴定",
        "value": "3"
      }, {
        "label": "包退",
        "value": "4"
      }, {
        "label": "已鉴定",
        "value": "5"
      }],
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
    addSelectItem() {
      this.attributeOptions.push({
        label: '',
        value: ''
      })
    },
    addLabels(){
      if(this.labelValue==null || this.labelValue==''){
        return
      }
      this.labelsOptions.push(this.labelValue)
      this.labelValue=''
    },
    setOptionValue(item, val) {
      item.value = isNumberStr(val) ? +val : val
    },
    handleIsOnline(row, column) {
      if (row.isOnline === 0) {
        return "下架";
      } else if (row.isOnline === 1) {
        return "上架";
      }
    },
    //1审核中2通过3不通过
    handleIsVerify(row) {
      if (row.isVerfiy == 2) {
        //审核不通过
        this.reset1();
        const id = row.id;
        this.form1 = { id: id, v: 2, refuseInfo: null };
        this.open1 = true;
        this.title1 = "审核不通过";
      } else {
        //审核通过
        var data = { id: row.id, v: 1 };
        changeState(data).then((response) => {
          this.getList();
        });
      }
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
    changeStateBtn(row) {
      changeState(this.form1).then((response) => {
        if (response.code === 200) {
          this.msgSuccess("修改成功");
          this.open1 = false;
          this.getList();
        }
      });
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
      } else if (row.cateCode === 101) {
        return "玉翠珠宝";
      } else if (row.cateCode === 102) {
        return "工艺作品";
      } else if (row.cateCode === 103) {
        return "文玩杂项";
      } else if (row.cateCode === 104) {
        return "紫砂陶瓷";
      } else if (row.cateCode === 105) {
        return "钱币邮票";
      } else if (row.cateCode === 106) {
        return "书画篆刻";
      } else if (row.cateCode === 107) {
        return "花鸟文娱";
      } else if (row.cateCode === 108) {
        return "其他";
      } else {
        return "--";
      }
    },
    selectDealType(value){
      if(value!=null && value==1){
        //一口价
        this.dealType1 = true
        this.dealType2 = false
      }else{
        //竞拍
        this.dealType2= true
        this.dealType1 = false
      }
    },
    selectIsOnline(value){
      if(value!=null && value==3){
        this.onLineTimeFlag = true;
      }else{
        this.onLineTimeFlag = false;
      }
    },
    selectCateCode(value) {
      this.attributeOptions=[]
      getAttribute(value).then((response)=>{
        console.info(response)
        this.attributeKeyOptions = response.rows
      })

    },
    getImgs() {
      return [];
    },
    /** 查询商品列表 */
    getList() {
      this.loading = true;
      this.queryParams.type = 1;
      listGoods(this.queryParams).then((response) => {
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
        info: null,
        createTime: null,
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
        labels:[],
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
        refuseInfo: null,
        v: null,
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
      this.form.labels= [];
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
        console.info(response.data.labels)
        if(response.data.labels==null || response.data.labels==undefined){
          response.data.labels = [];
        }else{
            response.data.labels = response.data.labels.split(",");
        }
        console.info(response.data.labels)
        this.form = response.data;
        if(response.data.dealType!=undefined && response.data.dealType!=null){
          var  value = response.data.dealType
          if(value!=null && value==1){
            //一口价
            this.dealType1 = true
            this.dealType2 = false
            if(response.data.isOnline!=undefined && response.data.isOnline==3){
              this.onLineTimeFlag = true;
            }
          }else{
            //竞拍
            this.dealType2= true
            this.dealType1 = false
          }
        }
        getAttribute(response.data.cateCode).then((response)=>{
          this.attributeKeyOptions = response
        })
        if(response.data.goodsAttribute!=undefined && response.data.goodsAttribute!=null){
          this.attributeOptions = JSON.parse(response.data.goodsAttribute)
        }
        // if(response.data.labels!=undefined && response.data.labels!=null){
        //   this.labelsOptions = JSON.parse(response.data.labels)
        // }
        this.open = true;
        this.title = "修改商品";
      });
    },

    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          this.form.source = 3;
          this.form.type = 1;
          this.form.orgId = 0;
          this.form.labels = this.form.labels.join(",")
          // this.form.imgs = this.imgfileList.join(",")
          if(this.attributeOptions!=undefined){
            this.form.goodsAttribute = JSON.stringify(this.attributeOptions)
          }
          // if(this.labelsOptions!=undefined){
          //   this.form.labels = JSON.stringify(this.labelsOptions)
          // }
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
