package com.base.action;




import com.base.util.JSONUtils;
import com.base.web.annotation.LoginMethod;
import com.ruoyi.common.core.domain.Ret;
import com.ruoyi.common.core.page.TableDataInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;

public class CoreController extends BaseController {
    private static final CoreController.GridMode GRID_MODE;

    public CoreController() {
    }

    protected Map<String, Object> getSessionAttribute() {
        Object obj = this.getSession().getAttribute(LoginMethod.ADMIN.getName());
        return obj == null ? null : (Map)obj;
    }

    protected void setSessionAttribute(Map<String, Object> obj) {
        this.setSessinAttribute(obj, LoginMethod.ADMIN);
    }

    protected Object getDefaultSessionParameter(String name) {
        Map<String, Object> map = this.getSessionAttribute();
        return map != null ? map.get(name) : null;
    }

    protected String getSessionParameter(String name) {
        Object object = this.getDefaultSessionParameter(name);
        return object == null ? "" : object.toString();
    }

    protected Integer getSessionParamterByInteger(String name) {
        Object object = this.getDefaultSessionParameter(name);
        return object == null ? null : (Integer)object;
    }

    /** @deprecated */
    @Deprecated
    protected Object getSessionParameter(String name, HttpSession session) {
        Object obj = session.getAttribute(LoginMethod.ADMIN.getName());
        if (obj != null) {
            HashMap<String, Object> map = (HashMap)obj;
            return map.get(name);
        } else {
            return "";
        }
    }

    protected void setSessionParameter(String name, Object value) {
        Map<String, Object> obj = this.getSessionAttribute();
        if (obj != null) {
            obj.put(name, value);
        }

    }

    /** @deprecated */
    @Deprecated
    protected void setSessionParameter(String name, Object value, HttpSession session) {
        Map<String, Object> obj = this.getSessionAttribute();
        if (obj != null) {
            obj.put(name, value);
        }

    }

    protected TableDataInfo page(List list) {
//        return GRID_MODE == CoreController.GridMode.grid ? JSONUtils.serialize(new Grid(PaginationSupport.getContext().getTotalCount(), list)) : JSONUtils.serialize(new JqGrid(PaginationSupport.getContext(), list));
        return getDataTable(list);
    }

    protected Ret ok() {
        return new Ret(200);
    }

    protected Ret ok(Object obj) {
        return new Ret(200,obj);
    }

    protected Ret ok(int code, Object obj) {
        return new Ret(code, obj);
    }

    protected Ret msg(int code) {
        return new Ret(code);
    }

    protected Ret msg(String msg) {
        return new Ret(200, msg);
    }

    protected Ret msg(int code, String msg) {
        return new Ret(code, msg);
    }

    static {
        GRID_MODE = CoreController.GridMode.grid;
    }

    public static enum GridMode {
        grid,
        jqgrid;

        private GridMode() {
        }
    }
}
