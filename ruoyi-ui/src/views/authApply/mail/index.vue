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
        label="宝贝名称"
        align="center"
        prop="name"
      >
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
          >为宝贝命名</el-button>
          <el-button
            type="text"
            size="mini"
            v-else
            @click="handleForm1(scope.row)"
          >{{ scope.row.name }}</el-button>
        </template>
      </el-table-column>

      <el-table-column
        label="物流单号"
        align="center"
        min-width="120%"
        prop="postExpressCode"
      >
        <template slot-scope="scope">
          <!-- <el-button
            type="text"
            size="mini"
            v-if="
              scope.row.postExpressCode == null ||
              scope.row.postExpressCode == undifined ||
              scope.row.postExpressCode == ''
            "
            @click="handleForm11(scope.row)"
          >物流单号</el-button> -->
          <el-button
            type="text"
            size="mini"
            v-if="scope.row.postExpressCode!=undefined && scope.row.postExpressCode!=null && scope.row.postExpressCode!=''"
            @click="handleForm11(scope.row)"
          >{{ scope.row.postExpressCode }}</el-button>
        </template>
      </el-table-column>

      <el-table-column
        label="宝贝图片"
        align="center"
        prop="images"
      >
        <template slot-scope="scope">
          <div v-if="scope.row.images != null">
            <span
              v-for="(item, index) in scope.row.images.split(',')"
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
          </div>
        </template>
      </el-table-column>

      <el-table-column
        label="宝贝附件"
        align="center"
        prop="goodsImgs"
      >
        <template slot-scope="scope">
          <div v-if="scope.row.goodsImgs != null">
            <span
              v-for="(item, index) in scope.row.goodsImgs.split(',')"
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
          </div>
        </template>
      </el-table-column>

      <el-table-column
        label="宝贝邮寄打包视频"
        align="center"
        prop="video"
      >
        <template slot-scope="scope">
          <el-button
            v-if="scope.row.goVideo"
            type="primary"
            @click="playVideo(scope.row, 1)"
            ref="btn"
            size="mini"
          >
            查看打包视频
          </el-button>
          <div v-else>未上传视频</div>
        </template>
      </el-table-column>

      <el-table-column
        label="快递状态"
        align="center"
        prop="postState"
        :formatter="handlePostState"
      />
      <el-table-column
        label="鉴定结果"
        align="center"
        prop="authResult"
        :formatter="handleAuthResult"
      />

      <el-table-column
        label="宝贝邮寄打包视频"
        align="center"
        prop="video"
      >
        <template slot-scope="scope">
          <el-button
            v-if="scope.row.openGoodsVideo"
            type="primary"
            @click="playVideo(scope.row, 2)"
            ref="btn"
            size="mini"
          >
            查看拆箱视频
          </el-button>
          <!-- handleForm6 -->

          <el-button
            type="primary"
            size="mini"
            v-else-if="(scope.row.postState==3 || scope.row.postState==4) && !scope.row.openGoodsVideo"
            @click="handleForm6(scope.row)"
          >上传拆箱视频</el-button>
          <div v-else>暂未上传拆箱视频</div>
        </template>
      </el-table-column>

      <el-table-column
        label="鉴宝视频"
        align="center"
        prop="video"
      >
        <template slot-scope="scope">
          <el-button
            v-if="scope.row.platGoodsAuthVideo"
            type="primary"
            @click="playVideo(scope.row, 3)"
            ref="btn"
            size="mini"
          >
            查看鉴宝视频
          </el-button>
          <el-button
            type="primary"
            size="mini"
            v-else-if="scope.row.authResult == 1 && scope.row.postState == 3"
            @click="handleForm2(scope.row)"
          >上传鉴宝视频</el-button>
        </template>
      </el-table-column>

      <el-table-column
        label="估价"
        align="center"
        prop="authPriceTest"
      >
        <template slot-scope="scope">
          <el-button
            type="info"
            size="mini"
            v-if="scope.row.authResult == 4 && scope.row.gotoPawn==0 && (scope.row.isSell==null || scope.row.isSell==0)"
            @click="handleForm12(scope.row)"
          >{{ scope.row.authPriceTest }}</el-button>
          <div v-else>{{ scope.row.authPriceTest }}</div>
        </template>
      </el-table-column>
      <el-table-column
        label="鉴定价"
        align="center"
        prop="authPrice"
      />

      <el-table-column
        label="鉴定说明"
        align="center"
        prop="appraisalDsc"
      >
        <template slot-scope="scope">
          <el-button
            type="primary"
            size="mini"
            v-if="!scope.row.appraisalDsc"
            @click="handleForm3(scope.row)"
          >填写鉴定说明</el-button>
          <el-button
            type="info"
            size="mini"
            v-else
            @click="handleForm3(scope.row)"
          >{{ scope.row.appraisalDsc }}</el-button>
        </template>
      </el-table-column>

      <el-table-column
        label="操作"
        align="center"
        prop="appraisalDsc"
      >
        <template slot-scope="scope">
          <span v-if="getOperation(scope.row) == 1"> </span>

          <el-button
            type="primary"
            size="mini"
            v-if="getOperation(scope.row) == 1"
            @click="handleForm6(scope.row)"
          >开始拆箱</el-button>
          <el-button
            type="primary"
            size="mini"
            v-if="getOperation(scope.row) == 2"
            @click="handleForm7(scope.row)"
          >开始鉴定</el-button>
          <!-- <el-button
            type="primary"
            size="mini"
            v-if="getOperation(scope.row) == 2"
            @click="handleForm4(scope.row)"
          >复制url</el-button> -->

          <el-button
            type="primary"
            size="mini"
            v-if="getOperation(scope.row) == 3"
            @click="handleForm7(scope.row)"
          >开始鉴定</el-button>
          <el-button
            type="primary"
            size="mini"
            v-if="getOperation(scope.row) == 3"
            @click="handleForm5(scope.row)"
          >专家鉴定</el-button>
          <span v-if="getOperation(scope.row) == 4">请先为宝贝命名</span>
          <el-button
            type="primary"
            size="mini"
            v-if="getOperation(scope.row) == 5"
            @click="handleForm8(scope.row)"
          >设置鉴定结果</el-button>
          <el-button
            type="primary"
            size="mini"
            v-if="getOperation(scope.row) == 6"
            @click="handleForm7(scope.row)"
          >再次鉴定</el-button>
          <span v-if="getOperation(scope.row) == 7 || getOperation(scope.row) == 9">已经认证！</span>
          <el-button
            type="primary"
            size="mini"
            v-if="getOperation(scope.row) == 8"
            @click="handleForm9(scope.row)"
          >确认</el-button>
          <span v-if="getOperation(scope.row) == 10">请先鉴定</span>
          <span v-if="getOperation(scope.row) == 11">宝贝已典当</span>
          <!-- <span v-if="scope.row.gotoPawn != 1">
            <span v-if="scope.row.power == 0">
              <span v-if="scope.row.isVerify != 1">
                <span
                  v-if="scope.row.isVerify == 0 && scope.row.postState == 4"
                >
                  <el-button
                    type="primary"
                    size="mini"
                    @click="handleForm6(scope.row)"
                    >开始拆箱</el-button
                  >
                </span>
                <span
                  v-else-if="
                    scope.row.isVerify == 0 && scope.row.postState == 3
                  "
                >
                  <span
                    v-if="
                      scope.row.authResult == 0 || scope.row.authResult == -1
                    "
                  >
                    <span v-if="scope.row.experterInfoId != 0">
                      <el-button
                        type="primary"
                        size="mini"
                        @click="handleForm7(scope.row)"
                        >开始鉴定</el-button
                      >
                      <el-button
                        type="primary"
                        size="mini"
                        @click="handleForm4(scope.row)"
                        >复制url</el-button
                      >
                    </span>
                    <span v-else>
                      <el-button
                        type="primary"
                        size="mini"
                        @click="handleForm7(scope.row)"
                        >开始鉴定</el-button
                      >
                      <el-button
                        type="primary"
                        size="mini"
                        @click="handleForm5(scope.row)"
                        >专家鉴定</el-button
                      >
                    </span>
                  </span>
                  <span
                    v-else-if="
                      scope.row.authResult == 1 &&
                      scope.row.platGoodsAuthVideo != '' &&
                      0 == scope.row.power
                    "
                  >
                    <span
                      v-if="
                        row.name == '' ||
                        row.name == undefined ||
                        row.name == 'undefined'
                      "
                    >
                      请先为宝贝命名
                    </span>
                    <span v-else>
                      <el-button
                        type="primary"
                        size="mini"
                        @click="handleForm8(scope.row)"
                        >设置鉴定结果</el-button
                      >
                    </span>
                  </span>
                  <span v-else-if="scope.row.authResult == 4">
                    <el-button
                      type="primary"
                      size="mini"
                      @click="handleForm7(scope.row)"
                      >再次鉴定</el-button
                    >
                  </span>
                </span>
              </span>
              <span v-else>已经认证！</span>
            </span>
            <span v-else-if="scope.row.power == 1">
              <span
                v-if="
                  scope.row.authResult == 2 ||
                  scope.row.authResult == 3 ||
                  scope.row.authResult == 4
                "
              >
                <el-button
                  type="primary"
                  size="mini"
                  v-if="scope.row.isVerify == 0"
                  @click="handleForm9(scope.row)"
                  >确认</el-button
                >
                <span v-else>'已经认证！</span>
              </span>
              <span v-else>请先鉴定</span>
            </span>
          </span>
          <span v-else>宝贝已典当</span> -->
        </template>
      </el-table-column>

      <el-table-column
        label="回寄给用户"
        align="center"
        prop="auth"
      >
        <template slot-scope="scope">
          <el-button
            type="primary"
            size="mini"
            v-if="
              scope.row.backState == 1
            "
            @click="handleForm10(scope.row)"
          >回寄给用户</el-button>
          <span v-if="scope.row.backState == 2">已经回寄给用户物流单号为：{{scope.row.backExpressCode}}</span>
          <span v-if="scope.row.backState == 3">用户已签收</span>
        </template>
      </el-table-column>
      <el-table-column
        label="查看"
        align="center"
      >
        <template slot-scope="scope">
          <el-button
            type="primary"
            size="mini"
            v-if="scope.row.postState==2"
            @click="confirmPost(scope.row)"
          >确认收货</el-button>
          <el-button
            size="mini"
            type="text"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['admin:goods:edit']"
          >查看详情</el-button>
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
          label="设置宝贝名称"
          prop="settleMoney"
          id="refuseInfo"
        >
          <el-input
            v-model="form1.name"
            placeholder="请输名称"
          />
        </el-form-item>
      </el-form>
      <div
        slot="footer"
        class="dialog-footer"
      >
        <el-button
          type="primary"
          @click="from1Submit"
        >确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog
      :title="title11"
      :visible.sync="open11"
      width="400px"
      append-to-body
    >
      <el-form
        ref="form11"
        :model="form11"
        label-width="150px"
      >
        <el-form-item
          label="设置物流单号"
          prop="postExpressCode"
          id="refuseInfo"
        >
          <el-input
            v-model="form11.postExpressCode"
            placeholder="请输名称"
          />
        </el-form-item>
      </el-form>
      <div
        slot="footer"
        class="dialog-footer"
      >
        <el-button
          type="primary"
          @click="from11Submit"
        >确 定</el-button>
        <el-button @click="cancel1">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog
      :title="title2"
      :visible.sync="open2"
      width="600px"
      append-to-body
    >
      <el-form
        ref="form2"
        :model="form2"
        label-width="100px"
      >
        <el-form-item
          label="上传鉴宝视频"
          prop="platGoodsAuthVideo"
          id="refuseInfo"
        >
          <video-upload
            v-model="form2.platGoodsAuthVideo"
            style="width: 300px; display: inline-block; margin-left: 10px"
          ></video-upload>
        </el-form-item>
      </el-form>
      <div
        slot="footer"
        class="dialog-footer"
      >
        <el-button
          type="primary"
          @click="from2Submit"
        >确 定</el-button>
        <el-button @click="cancel2">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog
      :title="title3"
      :visible.sync="open3"
      width="800px"
      append-to-body
    >
      <el-form
        :model="form3"
        label-width="100px"
      >

        <el-form-item
          label="名称"
          prop="name"
        >
          <el-input
            v-model="form3.name"
            placeholder="请输入名称"
          />
        </el-form-item>

        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item
              label="尺寸 "
              prop="size"
            >
              <el-input
                v-model="form3.length"
                placeholder="请输入尺寸"
              />
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item
              label="重量(单位g)"
              prop="weight"
            >
              <el-input
                v-model="form3.weight"
                placeholder="请输入重量"
              />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <!-- <el-col :span="12">
            <el-form-item label="材质" prop="material">
              <el-input
                v-model="form3.material"
                placeholder="请输入材质"
              /> </el-form-item
          ></el-col> -->
          <el-col :span="12">
            <el-form-item
              label="主体材质"
              prop="mainMaterial"
            >
              <el-input
                v-model="form3.mainMaterial"
                placeholder="请输入主体材质"
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item
              label="副材质"
              prop="material"
            >
              <el-input
                v-model="form3.material"
                placeholder="请输入副材质"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item
              label="创作年代"
              prop="createYear"
            >
              <el-input
                v-model="form3.createYear"
                placeholder="请输入创作年代"
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item
              label="其他"
              prop="other"
            >
              <el-input
                v-model="form3.other"
                placeholder="请输入其他"
              />
            </el-form-item>
          </el-col>
        </el-row>
        <!-- <el-form-item label="图片">
          <multi-upload ref="upload" v-model="imgfileList"></multi-upload>
        </el-form-item> -->
        <el-row>
          <el-col :span="12">
            <el-form-item
              label="市场流通性"
              prop="marketLiquidity"
            >
              <el-rate v-model="form3.marketLiquidity"></el-rate>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item
              label="价值稳定性"
              prop="valueStability"
            >
              <el-rate v-model="form3.valueStability"></el-rate>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">

            <el-form-item
              label="材质易损性"
              prop="materialVulnerability"
            >
              <el-rate v-model="form3.materialVulnerability"></el-rate>
            </el-form-item>
          </el-col>

        </el-row>
        <el-form-item
          label="鉴定说明"
          prop="appraisalDsc"
        >
          <el-input
            v-model="form3.appraisalDsc"
            placeholder="请输入鉴定说明"
          />
        </el-form-item>
      </el-form>
      <div
        slot="footer"
        class="dialog-footer"
      >
        <el-button
          type="primary"
          @click="from3Submit"
        >确 定</el-button>
        <el-button @click="cancel3">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog
      :title="title4"
      :visible.sync="open4"
      width="400px"
      append-to-body
    >
      <el-form
        ref="form4"
        :model="form4"
        label-width="100px"
      >
        <el-form-item
          label="链接地址"
          prop="settleMoney"
        >
          <el-input
            v-model="form4.authUrl"
            disabled
          />
        </el-form-item>
      </el-form>
    </el-dialog>

    <el-dialog
      :title="title5"
      :visible.sync="open5"
      width="400px"
      append-to-body
    >
      <el-form
        ref="form5"
        :model="form5"
        label-width="100px"
      >
        <el-form-item
          label="领域名称"
          prop="domainId"
        >
          <el-select
            v-model="form5.domainId"
            placeholder="请选择下拉选择"
            clearable
            :style="{ width: '100%' }"
            @change="selectDomain"
          >
            <el-option
              v-for="item in domainOptions"
              :key="item.id"
              :label="item.domainName"
              :value="item.id"
              :disabled="item.disabled"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item
          label="专家姓名"
          prop="experterId"
        >
          <el-select
            v-model="form5.experterId"
            placeholder="请选择下拉选择"
            clearable
            :style="{ width: '100%' }"
          >
            <el-option
              v-for="item in experterOptions"
              :key="item.id"
              :label="item.expertName"
              :value="item.id"
              :disabled="item.disabled"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div
        slot="footer"
        class="dialog-footer"
      >
        <el-button
          type="primary"
          @click="from5Submit"
        >确 定</el-button>
        <el-button @click="cancel5">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog
      :title="title6"
      :visible.sync="open6"
      width="600px"
      append-to-body
    >
      <el-form
        :model="form6"
        label-width="100px"
      >
        <el-form-item
          label="上传拆箱视频"
          prop="openGoodsVideo"
          id="refuseInfo"
        >
          <video-upload
            v-model="form6.openGoodsVideo"
            style="width: 300px; display: inline-block; margin-left: 10px"
          ></video-upload>
        </el-form-item>
      </el-form>
      <div
        slot="footer"
        class="dialog-footer"
      >
        <el-button
          type="primary"
          @click="from6Submit"
        >确 定</el-button>
        <el-button @click="cancel6">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog
      :title="title8"
      :visible.sync="open8"
      width="400px"
      append-to-body
    >
      <el-form
        :model="form8"
        label-width="100px"
      >
        <el-form-item
          label="鉴定结果"
          prop="authResult"
        >
          <el-select
            v-model="form8.authResult"
            placeholder="请选择下拉选择"
            clearable
            :style="{ width: '100%' }"
            @change="selectAuthResult"
          >
            <el-option
              v-for="item in authResultOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>

        <el-form-item
          label="鉴定价"
          prop="authPrice"
          v-if="show4"
        >
          <el-input v-model="form8.authPrice" />
        </el-form-item>
        <el-form-item
          label="月利率(%)"
          prop="moneyRate"
          v-if="show4"
        >
          <el-input v-model="form8.moneyRate" />
        </el-form-item>
        <el-form-item
          label="月费率(%)"
          prop="rate"
          v-if="show4"
        >
          <el-input v-model="form8.rate" />
        </el-form-item>

        <el-form-item
          label="无法鉴定理由："
          prop="authPrice"
          v-if="show2"
        >
          <el-input v-model="form8.notVerifyReason" />
        </el-form-item>
      </el-form>
      <div
        slot="footer"
        class="dialog-footer"
      >
        <el-button
          type="primary"
          @click="from8Submit"
        >确 定</el-button>
        <el-button @click="cancel8">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog
      :title="title10"
      :visible.sync="open10"
      width="600px"
      append-to-body
    >
      <el-form
        :model="form10"
        label-width="100px"
      >

        <el-form-item
          label="收件人名字"
          prop="backUserName"
        >
          <el-input
            v-model="form10.backUserName"
            placeholder="请输入收件人名字"
          />
        </el-form-item>

        <el-form-item
          label="收件人电话"
          prop="backUserPhone"
        >
          <el-input
            v-model="form10.backUserPhone"
            placeholder="请输入收件人电话"
          />
        </el-form-item>

        <el-form-item
          label="收件人地址"
          prop="backUserName"
        >
          <el-input
            v-model="form10.backUserExpress"
            placeholder="请输入收件人地址"
          />
        </el-form-item>

        <el-form-item label="快递公司">
          顺丰快递
        </el-form-item>

        <el-form-item
          label="快递单号"
          prop="backExpressCode"
        >
          <el-input
            v-model="form10.backExpressCode"
            placeholder="请输入快递单号"
          />
        </el-form-item>

        <el-form-item
          label="上传物流凭证"
          prop="backImages"
        >
          <single-upload
            v-model="form10.backImages"
            style="width: 300px; display: inline-block; margin-left: 10px"
          ></single-upload>
        </el-form-item>
        <el-form-item
          label="宝贝装箱视频"
          prop="backVideo"
          id="refuseInfo"
        >
          <video-upload
            v-model="form10.backVideo"
            style="width: 300px; display: inline-block; margin-left: 10px"
          ></video-upload>
        </el-form-item>
      </el-form>
      <div
        slot="footer"
        class="dialog-footer"
      >
        <el-button
          type="primary"
          @click="from10Submit"
        >确 定</el-button>
        <el-button @click="cancel10">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog
      :title="title12"
      :visible.sync="open12"
      width="400px"
      append-to-body
    >
      <el-form
        ref="form12"
        :model="form12"
        label-width="100px"
      >
        <el-form-item
          label="设置估价"
          prop="authPriceTest"
          id="authPriceTest"
        >
          <el-input
            v-model="form12.authPriceTest"
            placeholder="请输入价格"
          />
        </el-form-item>
      </el-form>
      <div
        slot="footer"
        class="dialog-footer"
      >
        <el-button
          type="primary"
          @click="from12Submit"
        >确 定</el-button>
        <el-button @click="cancel12">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog
      title
      :visible.sync="videoOpen"
      width="40%"
      @close="closeDialog"
    >
      <video
        :src="videoUrl"
        class="avatar"
        preload="auto"
        controls="controls"
        width="100%"
      ></video>
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
        label-width="150px"
      >
        <el-col :span="12">
          <el-form-item
            label="宝贝原始主人账号"
            prop="content"
          >
            <el-input
              v-model="form.oldAccount"
              disabled
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item
            label="用户账号"
            prop="content"
          >
            <el-input
              v-model="form.account"
              disabled
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item
            label="用户昵称"
            prop="content"
          >
            <el-input
              v-model="form.nickName"
              disabled
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item
            label="宝贝名称"
            prop="content"
          >
            <el-input
              v-model="form.name"
              disabled
            ></el-input>
          </el-form-item>
        </el-col>
        <el-form-item
          label="邮寄打包视频"
          prop="goVideo"
        >
          <template slot-scope="scope">
            <video
              v-if="form.goVideo"
              class="avatar"
              preload="auto"
              width="320"
              height="320"
              controls="controls"
              :src="form.goVideo"
            ></video>
          </template>
        </el-form-item>
        <el-form-item
          label="拆箱视频"
          prop="openGoodsVideo"
        >
          <template slot-scope="scope">
            <video
              v-if="form.openGoodsVideo"
              class="avatar"
              preload="auto"
              width="320"
              height="320"
              controls="controls"
              :src="form.openGoodsVideo"
            ></video>
          </template>
        </el-form-item>
        <el-col :span="12">
          <el-form-item
            label="物流单号"
            prop="content"
          >
            <el-input
              v-model="form.postExpressCode"
              disabled
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item
            label="顺风保价价格"
            prop="content"
          >
            <el-input
              v-model="form.sfProtectPrice"
              disabled
            ></el-input>
          </el-form-item>
        </el-col>
        <el-form-item
          label="无法鉴定理由"
          prop="content"
        >
          <el-input
            v-model="form.notVerifyReason"
            disabled
          ></el-input>
        </el-form-item>
        <el-form-item
          label="专家姓名"
          prop="content"
          v-if="show"
        >
          <el-input
            v-model="form.experterName"
            disabled
          ></el-input>
        </el-form-item>
        <el-form-item
          label="图片初审预估价"
          prop="content"
          v-if="show"
        >
          <el-input
            v-model="form.authPriceTest"
            disabled
          ></el-input>
        </el-form-item>
        <el-form-item
          label="专家意见"
          prop="content"
          v-if="show"
        >
          <el-input
            v-model="form.experterInfo"
            disabled
          ></el-input>
        </el-form-item>
        <el-form-item
          label="用户物流数据"
          prop="content"
        >
          <el-input
            v-model="form.expressData"
            disabled
          ></el-input>
        </el-form-item>
        <el-form-item
          label="平台回寄单号"
          prop="content"
        >
          <el-input
            v-model="form.backExpressCode"
            disabled
          ></el-input>
        </el-form-item>
        <el-form-item
          label="平台回寄物流数据"
          prop="content"
        >
          <el-input
            v-model="form.expressData2"
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
  authAdminVerify,
  beginToOper,
  checkDetail,
  settle,
  saveByBackToUser,
  changeCheck,
  exportGoods,
} from "@/api/postStore/goods";
import { listDomain } from "@/api/authApply/domain";
import { experterList } from "@/api/authApply/experter";
import { saveInfo } from "@/api/authApply/experterInfo";
import SingleUpload from "@/components/Upload/singleUpload";
import VideoUpload from "@/components/Upload/videoUpload";
import MultiUpload from "@/components/Upload/multiUpload";
import {
  getCertificate,
  updateCertificate,
  getByUserGoodsId,
} from "@/api/certificate/certificate";

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
      open3: false,
      open4: false,
      open5: false,
      open6: false,
      open8: false,
      open10: false,
      open11: false,
      open12: false,
      videoOpen: false,
      videoUrl: null,
      show2: true,
      show4: false,
      show: false,
      title1: "",
      title2: "",
      title3: "",
      title4: "",
      title5: "",
      title6: "",
      title8: "",
      title10: "",
      title11: "",
      title12: "",

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
      form2: {},
      form3: {},
      form4: {},
      form5: {},
      form6: {},
      form8: {},
      form10: {},
      form11: {},
      form12: {},
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
      domainOptions: [],
      experterOptions: [],
      authResultOptions: [
        {
          label: "无法鉴定",
          value: 2,
        },
        {
          label: "可以鉴定",
          value: 4,
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
    this.getDomainList();
  },
  methods: {
    reload() {
      console.info("reload");
      this.isRouterAlive = false;
      this.$nextTick(() => (this.isRouterAlive = true));
    },
    getOperation(row) {
      if (row.gotoPawn != 1) {
        if (row.power == 0) {
          if (row.isVerify != 1) {
            if (row.postState == 4 && row.isVerify == 0) {
              return 1;
            } else if (row.postState == 3 && row.isVerify == 0) {
              if (row.authResult == 0 || row.authResult == -1) {
                if (row.experterInfoId != 0) {
                  return 2;
                } else {
                  return 3;
                }
              } else if (
                row.authResult == 1 &&
                row.platGoodsAuthVideo != "" &&
                0 == row.power
              ) {
                if (
                  row.name == "" ||
                  row.name == undefined ||
                  row.name == "undefined"
                ) {
                  return 4;
                } else {
                  return 5;
                }
              } else if (row.authResult == 4) {
                return 6;
              }
            }
          } else {
            return 7;
          }
        } else if (row.power == 1) {
          if (
            2 == row.authResult ||
            3 == row.authResult ||
            4 == row.authResult
          ) {
            if (row.isVerify == 0) {
              return 8;
            } else {
              return 9;
            }
          } else {
            return 10;
          }
        }
      } else {
        return 11;
      }
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
    handleForm2(row) {
      this.reset2();
      const id = row.id;
      this.form2 = { id: id, platGoodsAuthVideo: row.platGoodsAuthVideo };
      this.open2 = true;
      this.title2 = "上传鉴宝视频";
    },
    handleForm3(row) {
      this.reset3();
      const id = row.id;
      getByUserGoodsId(id).then((response) => {
        console.info(response)
         if(response.data!=undefined && response.data!=null){
            this.form3 =response.data;
         }else{
            this.form3.userGoodsId = id;
         }

        this.open3 = true;
        this.title3 = "鉴定说明";

      });
    },
    handleForm4(row) {
      this.reset4();
      const id = row.id;
      this.form4 = { id: id, authUrl: null };
      this.open4 = true;
      this.title4 = "复制url";
    },
    handleForm5(row) {
      this.reset5();
      this.form5 = { goodsId: row.id, state: 0 };
      console.info(this.form5);
      this.open5 = true;
      this.title5 = "专家鉴定";
    },
    handleForm6(row) {
      this.reset6();
      const id = row.id;
      this.form6 = { id: id, openGoodsVideo: row.openGoodsVideo };
      this.open6 = true;
      this.title6 = "上传拆箱视频";
    },
    handleForm7(row) {
      updateGoods({ id: row.id, authResult: 1 }).then((response) => {
        if (response.code === 200) {
          this.msgSuccess("修改成功");
          this.getList();
        }
      });
    },
    handleForm8(row) {
      this.reset8();
      const id = row.id;
      this.form8 = { id: id };
      this.open8 = true;
      this.title8 = "设置鉴定结果";
    },
    handleForm9(row) {
      authAdminVerify({ id: row.id, isVerify: 1 }).then((response) => {
        if (response.code === 200) {
          this.msgSuccess("修改成功");
          this.getList();
        }
      });
    },
     handleForm10(row) {
      this.reset10();
      const id = row.id;
      this.form10 = { id: id };
      this.open10 = true;
      this.title10 = "回寄给用户";
    },
     handleForm11(row) {
      this.reset11();
      const id = row.id;
      this.form11 = { id: id, postExpressCode: row.postExpressCode };
      this.open11 = true;
      this.title11 = "物流单号";
    },
     handleForm12(row) {
      this.reset12();
      const id = row.id;
      this.form12 = { id: id, authPriceTest: row.authPriceTest };
      this.open12 = true;
      this.title12 = "估价";
    },
    playVideo(row,type) {
      this.videoOpen = true;
      if(type==1){
        this.videoUrl = row.goVideo;
      }else if(type==2){
        this.videoUrl = row.openGoodsVideo;
      }else if(type==3){
        this.videoUrl = row.platGoodsAuthVideo;
      }
      
      // this.videoUrl =
      //   "http://baidu.paidangwang.net/admin/download?id=c379cda3f709491c867c39e8cd6cb6c4";
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
    from2Submit() {
      updateGoods(this.form2).then((response) => {
        if (response.code === 200) {
          this.msgSuccess("修改成功");
          this.open2 = false;
          this.getList();
        }
      });
    },
    from3Submit() {
      updateCertificate(this.form3).then((response) => {
        if (response.code === 200) {
          this.msgSuccess("修改成功");
          this.open3 = false;
          this.getList();
        }
      });
    },
    from4Submit() {
      updateGoods(this.form4).then((response) => {
        if (response.code === 200) {
          this.msgSuccess("修改成功");
          this.open4 = false;
          this.getList();
        }
      });
    },
    from5Submit() {
      console.info(this.form5);
      saveInfo(this.form5).then((response) => {
        if (response.code === 200) {
          this.msgSuccess("修改成功");
          this.open5 = false;
          this.getList();
        }
      });
    },
    from6Submit() {
      this.form6.location=1;
      this.form6.postState = 3;
      updateGoods(this.form6).then((response) => {
        if (response.code === 200) {
          this.msgSuccess("修改成功");
          this.open6 = false;
          this.getList();
        }
      });
    },

    from8Submit() {
      this.form8.isVerify=1;
      beginToOper(this.form8).then((response) => {
        if (response.code === 200) {
          this.msgSuccess("修改成功");
          this.open8 = false;
          this.getList();
        }
      });
    },

     from10Submit() {
      this.form10.backState=2;
      this.form10.backExpress='顺丰快递';
      saveByBackToUser(this.form10).then((response) => {
        if (response.code === 200) {
          this.msgSuccess("修改成功");
          this.open10 = false;
          this.getList();
        }
      });
    },
     from11Submit() {
      
      updateGoods(this.form11).then((response) => {
        if (response.code === 200) {
          this.msgSuccess("修改成功");
          this.open11 = false;
          this.getList();
        }
      });
    },
    from12Submit() {
      updateGoods(this.form12).then((response) => {
        if (response.code === 200) {
          this.msgSuccess("修改成功");
          this.open12 = false;
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
    handlePostState(row, column) {
      if (row.postState == 2) {
        return "邮寄中";
      } else if (row.postState == 3) {
        return "平台确认";
      } else if (row.postState == 4) {
        return "已经到货";
      } else {
        return "";
      }
    },
    handleAuthResult(row, column) {
      if (row.authResult == 0) {
        return "未鉴定";
      } else if (row.authResult == 1) {
        return "鉴定中";
      } else if (row.authResult == 2) {
        return "无法鉴定";
      } else if (row.authResult == 3) {
        return "赝品";
      } else if (row.authResult == 4) {
        return "真品";
      } else {
        return "";
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
    selectDomain(value) {
      experterList({ domainId: value }).then((response) => {
        this.experterOptions = response;
      });
    },
    selectAuthResult(value) {
      if (value != null && value === 2) {
        this.show2 = true;
        this.show4 = false;
      } else if (value != null && value === 4) {
        this.show2 = false;
        this.show4 = true;
      }
    },
    getImgs() {
      return [];
    },
    /** 查询商品列表 */
    getList() {
      this.loading = true;
      this.queryParams.type = "mail";
      goodsList(this.queryParams).then((response) => {
        this.goodsList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    //专业领域
    getDomainList() {
      listDomain({}).then((response) => {
        this.domainOptions = response;
        console.info("domainOptions");
        console.info(this.domainOptions);
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    cancel1() {
      this.open11 = false;
      this.reset11();
    },
    cancel2() {
      this.open2 = false;
      this.reset2();
    },
    cancel3() {
      this.open3 = false;
      this.reset3();
    },
    cancel4() {
      this.open4 = false;
      this.reset4();
    },
    cancel5() {
      this.open5 = false;
      this.reset5();
    },
    cancel6() {
      this.open6 = false;
      this.reset5();
    },
    cancel8() {
      this.open8 = false;
      this.reset5();
    },
      cancel10() {
      this.open8 = false;
      this.reset5();
    },
       cancel12() {
      this.open12 = false;
      this.reset12();
    },
    closeDialog() {
      this.videoUrl = "";
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        images: null,
        video: null,
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
    reset2() {
      this.form2 = {
        id: null,
        platGoodsAuthVideo: null,
      };
      this.resetForm("form2");
    },
    reset3() {
      this.form3 = {
        id: null,
        appraisalDsc: null,
      };
      this.resetForm("form3");
    },
    reset4() {
      this.form4 = {
        id: null,
        authUrl: null,
      };
      this.resetForm("form4");
    },
    reset5() {
      this.form5 = {
        domainId: null,
        experterId: null,
        goodsId: null,
        state: 0,
      };
      this.resetForm("form5");
    },
    reset6() {
      this.form6 = {
        id: null,
        openGoodsVideo: null,
      };
      this.resetForm("form6");
    },
    reset8() {
      this.form8 = {
        id: null,
      };
      this.resetForm("form8");
    },
     reset10() {
      this.form10 = {
        id: null,
      };
      this.resetForm("form10");
    },
     reset11() {
      this.form11 = {
        id: null,
        postExpressCode: null,
      };
      this.resetForm("form11");
    },
      reset12() {
      this.form12 = {
        id: null,
        authePriceTest: null,
      };
      this.resetForm("form12");
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
    confirmPost(row){
       updateGoods({ id: row.id,postState:3 }).then((response) => {
              this.msgSuccess("修改成功");
              this.getList();
       })
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      checkDetail({ id: row.id }).then((response) => {
        console.info(response);

        if (
          response.data.experterId != null &&
          response.data.experterId != undefined &&
          response.data.experterId != 0
        ) {
          this.show = true;
        }
        if (response.data.expressData) {
          var a = JSON.parse(response.data.expressData);
          response.data.expressData = a.data[0].context;
        }
        if (response.data.expressData2) {
          var a = JSON.parse(response.data.expressData2);
          response.data.expressData2 = a.data[0].context;
        }
        this.form = response.data;
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
