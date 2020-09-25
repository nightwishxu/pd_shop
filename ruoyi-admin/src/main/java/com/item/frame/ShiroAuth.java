//package com.item.frame;
//
//import com.base.security.entity.ShiroRight;
//import com.base.security.entity.ShiroRole;
//import com.base.security.shiro.Principal;
//import com.base.security.shiro.realm.ReamAuth;
//import com.base.security.shiro.token.UsernamePasswordToken;
//import com.base.util.ClassUtils;
//import com.base.web.annotation.LoginMethod;
//import com.item.dao.model.Right;
//import com.item.dao.model.Role;
//import com.item.dao.model.UserGroup;
//import com.item.util.RoleUtil;
//import org.apache.commons.beanutils.BeanUtils;
//import org.apache.commons.lang.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * @author: Sun
// * @date: 2018/10/18
// */
//@Component
//public class ShiroAuth implements ReamAuth {
//    private Logger logger = LoggerFactory.getLogger(ShiroAuth.class);
//
//    @Override
//    public Object invoke(UsernamePasswordToken token) {
//        String password = new String(token.getPassword());
//        UserGroup group = RoleUtil.getGroupByCode(token.getGroup());
//        Map<String, Object> params = new HashMap<>();
//        params.put("account", token.getUsername());
//        params.put("password", password);
//        Object object = null;
//        try {
//            object = ClassUtils.invokeMethod(group.getClassName(), "login", params);
//        } catch (Exception e) {
//            logger.error(token.getClassName()+"中没有定义login(account,password)方法");
//            return null;
//        }
//        if (object != null){
//            Principal principal = new Principal();
//            String roleCode = null;
//            try {
//                roleCode = BeanUtils.getProperty(object,"roleCode");
//                principal.setId(Integer.parseInt(BeanUtils.getProperty(object, "id")));
//                principal.setRoleCode(roleCode);
//                principal.setAccount(token.getUsername());
//                principal.setGroupCode(token.getGroup());
//                principal.setOrgName(group.getName());
////				principal.setAreaCode(admin.getAreaCode());
////				principal.setName(admin.getName());
////				principal.setAdminType(admin.getType());
////				if(admin.getType() > 2){
////					Org org = orgService.selectByPrimaryKey(admin.getOrgId());
////					principal.setOrgId(admin.getOrgId());
////					principal.setOrgName(org.getName());
////				}else{
////				}
//
//                principal.setLoginMethod(LoginMethod.ADMIN);
//                List<Right> rights = null;
//                if (StringUtils.isBlank(roleCode)){
//                    //rights = rightService.selectByUser(principal.getId());
//                }else {
//                    Role role = RoleUtil.getRoleByCode(roleCode);
//                    if (role == null){
//                        return null;
//                    }
//                    ShiroRole shiroRole = new ShiroRole();
//                    shiroRole.setCode(role.getCode());
//                    shiroRole.setId(role.getId().toString());
//                    shiroRole.setName(role.getName());
//                    principal.addRole(shiroRole);
//                    rights = RoleUtil.getRightsByCode(group.getCode(),roleCode);
//                    if (!RoleUtil.isGroupRole(roleCode, token.getGroup())){
//                        return null;
//                    }
//                }
//                List<ShiroRight> realRight = new ArrayList<>();
//                List<ShiroRight> menu = new ArrayList<>();
//                if (rights != null) {
//                    for (Right right : rights) {
//                        if (right == null) {
//                            continue;
//                        }
//                        ShiroRight real = new ShiroRight();
//                        real.setCode(right.getCode());
//                        real.setIcon(right.getIcon());
//                        real.setId(right.getId().toString());
//                        real.setName(right.getName());
//                        real.setParentId(right.getParentId().toString());
//                        real.setUrl(right.getUrl());
//                        if (right.getLevel() == 3) {
//                            menu.add(real);
//                        } else {
//                            realRight.add(real);
//                        }
//                    }
//                }
//                //添加菜单权限
//                principal.setRights(realRight);
//                //添加按钮权限
//                principal.setMenus(menu);
//            } catch (Exception e) {
//                logger.error("对象没有定义id或者roleCode字段");
//                principal = null;
//            }
//            return principal;
//        }
//        return null;
//    }
//}
