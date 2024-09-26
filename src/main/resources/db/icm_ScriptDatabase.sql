--INSERT ROLES
insert into tb_role(id,active,name)
values(nextval('id_sequence'),true,'ROLE_ICM_ADMIN');
insert into tb_role(id,active,name)
values(nextval('id_sequence'),true,'ROLE_ICM_USER');
insert into tb_role(id,active,name)
values(nextval('id_sequence'),true,'ROLE_CU_ADMIN');
insert into tb_role(id,active,name)
values(nextval('id_sequence'),true,'ROLE_CU_MEMBER');
select * from tb_role;

--INSERT ICM_COMPANY
select u.id from tb_user as u;
insert into tb_company(id,code,email,host_url,name,national_code,phone,register_date,fk_register_user,fk_address)
values(nextval('id_sequence'),'ICM_1',null,'http://www.icm.com','ICM_COMPANY','48759655','09359974976',NOW(),12,null);
select * from tb_company;


--INSERT Link ICM_COMPANY to DEFAULT ICM USER
select u.id from tb_user as u;
select co.id from tb_company as co;
insert into tb_company_user(id,register_date,fk_register_user,fk_company,fk_user)
values(nextval('id_sequence'),NOW(),12,13,12);
select * from tb_company_user;




--INSERT Types
--INSERT GENDER TYPE
insert into tb_management_type(id,code,priority,sub_type,title)
values(nextval('id_sequence'),'gender',1,'woman','woman');
insert into tb_management_type(id,code,priority,sub_type,title)
values(nextval('id_sequence'),'gender',2,'man','man');
insert into tb_management_type(id,code,priority,sub_type,title)
values(nextval('id_sequence'),'gender',3,'other','other');

--INSERT ACCESS TYPE
insert into tb_management_type(id,code,priority,sub_type,title)
values(nextval('id_sequence'),'access_type',1,'write','write');
insert into tb_management_type(id,code,priority,sub_type,title)
values(nextval('id_sequence'),'access_type',2,'read','read');
insert into tb_management_type(id,code,priority,sub_type,title)
values(nextval('id_sequence'),'access_type',3,'update','update');
insert into tb_management_type(id,code,priority,sub_type,title)
values(nextval('id_sequence'),'access_type',4,'delete','delete');

--INSERT LOCATION TYPE
insert into tb_management_type(id,code,priority,sub_type,title)
values(nextval('id_sequence'),'location',1,'country','country');
insert into tb_management_type(id,code,priority,sub_type,title)
values(nextval('id_sequence'),'location',2,'province','province');
insert into tb_management_type(id,code,priority,sub_type,title)
values(nextval('id_sequence'),'location',3,'city','city');

--INSERT PAGE TYPE
insert into tb_management_type(id,code,priority,sub_type,title)
values(nextval('id_sequence'),'page_type',1,'faq','faq');
insert into tb_management_type(id,code,priority,sub_type,title)
values(nextval('id_sequence'),'page_type',2,'news','news');
insert into tb_management_type(id,code,priority,sub_type,title)
values(nextval('id_sequence'),'page_type',3,'blog','blog');
insert into tb_management_type(id,code,priority,sub_type,title)
values(nextval('id_sequence'),'page_type',4,'tutorial','tutorial');
insert into tb_management_type(id,code,priority,sub_type,title)
values(nextval('id_sequence'),'page_type',5,'gallery','gallery');

--INSERT CONTENT TYPE
insert into tb_management_type(id,code,priority,sub_type,title)
values(nextval('id_sequence'),'content_type',1,'only_text','only_text');
insert into tb_management_type(id,code,priority,sub_type,title)
values(nextval('id_sequence'),'content_type',2,'only_image','only_image');
insert into tb_management_type(id,code,priority,sub_type,title)
values(nextval('id_sequence'),'content_type',3,'text_image','text_image');

--INSERT FILE TYPE
insert into tb_management_type(id,code,priority,sub_type,title)
values(nextval('id_sequence'),'file_type',1,'jpg','jpg');
insert into tb_management_type(id,code,priority,sub_type,title)
values(nextval('id_sequence'),'file_type',2,'png','png');
insert into tb_management_type(id,code,priority,sub_type,title)
values(nextval('id_sequence'),'file_type',3,'gif','gif');

select * from tb_management_type;



--INSERT DEFAULT MENU FOR ICM Company
select u.id from tb_user as u;-- >> 12;
select co.id from tb_company as co; -- >> 13;

insert into tb_menu(id,active,icon_path,register_date,fk_register_user,title,url,fk_company)
values(nextval('id_sequence'),true,null,NOW(),12,null,'/icm/user/login',13);
insert into tb_menu(id,active,icon_path,register_date,fk_register_user,title,url,fk_company)
values(nextval('id_sequence'),true,null,NOW(),12,null,'/icm/user/logout',13);
insert into tb_menu(id,active,icon_path,register_date,fk_register_user,title,url,fk_company)
values(nextval('id_sequence'),true,null,NOW(),12,null,'/icm/user/v1/getIcmUser',13);
insert into tb_menu(id,active,icon_path,register_date,fk_register_user,title,url,fk_company)
values(nextval('id_sequence'),true,null,NOW(),12,null,'/icm/user/v1/registerIcmUser',13);
insert into tb_menu(id,active,icon_path,register_date,fk_register_user,title,url,fk_company)
values(nextval('id_sequence'),true,null,NOW(),12,null,'/icm/user/v1/updateIcmUser',13);
insert into tb_menu(id,active,icon_path,register_date,fk_register_user,title,url,fk_company)
values(nextval('id_sequence'),true,null,NOW(),12,null,'/icm/user/v1/getAllIcmUser',13);
insert into tb_menu(id,active,icon_path,register_date,fk_register_user,title,url,fk_company)
values(nextval('id_sequence'),true,null,NOW(),12,null,'/icm/baseInfo/v1/getGenderTypes',13);
insert into tb_menu(id,active,icon_path,register_date,fk_register_user,title,url,fk_company)
values(nextval('id_sequence'),true,null,NOW(),12,null,'/icm/baseInfo/v1/getIcmRoles',13);
insert into tb_menu(id,active,icon_path,register_date,fk_register_user,title,url,fk_company)
values(nextval('id_sequence'),true,null,NOW(),12,null,'/icm/baseInfo/v1/getLocationTypes',13);
insert into tb_menu(id,active,icon_path,register_date,fk_register_user,title,url,fk_company)
values(nextval('id_sequence'),true,null,NOW(),12,null,'/icm/baseInfo/v1/getAccessLevelTypes',13);
insert into tb_menu(id,active,icon_path,register_date,fk_register_user,title,url,fk_company)
values(nextval('id_sequence'),true,null,NOW(),12,null,'/icm/accessManagement/v1/setIcmUserRoles',13);
insert into tb_menu(id,active,icon_path,register_date,fk_register_user,title,url,fk_company)
values(nextval('id_sequence'),true,null,NOW(),12,null,'/icm/accessManagement/v1/setIcmAccessLevels',13);
insert into tb_menu(id,active,icon_path,register_date,fk_register_user,title,url,fk_company)
values(nextval('id_sequence'),true,null,NOW(),12,null,'/icm/baseInfo/v1/getIcmMenuAccessLevelList',13);
insert into tb_menu(id,active,icon_path,register_date,fk_register_user,title,url,fk_company)
values(nextval('id_sequence'),true,null,NOW(),12,null,'/icm/accessManagement/v1/deleteIcmUserRoles',13);
insert into tb_menu(id,active,icon_path,register_date,fk_register_user,title,url,fk_company)
values(nextval('id_sequence'),true,null,NOW(),12,null,'/icm/user/v1/registerUser',13);
insert into tb_menu(id,active,icon_path,register_date,fk_register_user,title,url,fk_company)
values(nextval('id_sequence'),true,null,NOW(),12,null,'/icm/user/v1/getUser',13);
insert into tb_menu(id,active,icon_path,register_date,fk_register_user,title,url,fk_company)
values(nextval('id_sequence'),true,null,NOW(),12,null,'/icm/accessManagement/v1/setAccessLevels',13);
insert into tb_menu(id,active,icon_path,register_date,fk_register_user,title,url,fk_company)
values(nextval('id_sequence'),true,null,NOW(),12,null,'/icm/accessManagement/v1/setUserRoles',13);
insert into tb_menu(id,active,icon_path,register_date,fk_register_user,title,url,fk_company)
values(nextval('id_sequence'),true,null,NOW(),12,null,'/icm/baseInfo/v1/getRoles',13);
insert into tb_menu(id,active,icon_path,register_date,fk_register_user,title,url,fk_company)
values(nextval('id_sequence'),true,null,NOW(),12,null,'/icm/baseInfo/v1/registerManagementType',13);
insert into tb_menu(id,active,icon_path,register_date,fk_register_user,title,url,fk_company)
values(nextval('id_sequence'),true,null,NOW(),12,null,'/icm/baseInfo/v1/updateManagementType',13);
insert into tb_menu(id,active,icon_path,register_date,fk_register_user,title,url,fk_company)
values(nextval('id_sequence'),true,null,NOW(),12,null,'/icm/user/v1/delete',13);

select * from tb_menu;


--INSERT USER_ACCESS
insert into tb_user_access(id,fk_role,fk_user)
values(nextval('id_sequence'),(select r.id from tb_role as r where r.name = 'ROLE_ICM_ADMIN'),(select u.id from tb_user as u where u.username= 'icmAdmin'));
select * from tb_user_access;

--INSERT ACCESS_LEVEL_ON_MENU
--select ua.id from tb_user_access as ua where ua.fk_user = (select u.id from tb_user as u where u.username= 'icmAdmin');
select mn.id from tb_menu as mn;

insert into tb_menu_access_level as mal(id,access_delete,access_read,access_update,access_write,fk_menu,fk_user_access)
values(nextval('id_sequence'),true,true,true,true,36,59);

insert into tb_menu_access_level as mal(id,access_delete,access_read,access_update,access_write,fk_menu,fk_user_access)
values(nextval('id_sequence'),true,true,true,true,37,59);

insert into tb_menu_access_level as mal(id,access_delete,access_read,access_update,access_write,fk_menu,fk_user_access)
values(nextval('id_sequence'),true,true,true,true,38,59);

insert into tb_menu_access_level as mal(id,access_delete,access_read,access_update,access_write,fk_menu,fk_user_access)
values(nextval('id_sequence'),true,true,true,true,39,59);

insert into tb_menu_access_level as mal(id,access_delete,access_read,access_update,access_write,fk_menu,fk_user_access)
values(nextval('id_sequence'),true,true,true,true,40,59);

insert into tb_menu_access_level as mal(id,access_delete,access_read,access_update,access_write,fk_menu,fk_user_access)
values(nextval('id_sequence'),true,true,true,true,41,59);

insert into tb_menu_access_level as mal(id,access_delete,access_read,access_update,access_write,fk_menu,fk_user_access)
values(nextval('id_sequence'),true,true,true,true,42,59);

insert into tb_menu_access_level as mal(id,access_delete,access_read,access_update,access_write,fk_menu,fk_user_access)
values(nextval('id_sequence'),true,true,true,true,43,59);

insert into tb_menu_access_level as mal(id,access_delete,access_read,access_update,access_write,fk_menu,fk_user_access)
values(nextval('id_sequence'),true,true,true,true,44,59);

insert into tb_menu_access_level as mal(id,access_delete,access_read,access_update,access_write,fk_menu,fk_user_access)
values(nextval('id_sequence'),true,true,true,true,45,59);

insert into tb_menu_access_level as mal(id,access_delete,access_read,access_update,access_write,fk_menu,fk_user_access)
values(nextval('id_sequence'),true,true,true,true,46,59);

insert into tb_menu_access_level as mal(id,access_delete,access_read,access_update,access_write,fk_menu,fk_user_access)
values(nextval('id_sequence'),true,true,true,true,47,59);

insert into tb_menu_access_level as mal(id,access_delete,access_read,access_update,access_write,fk_menu,fk_user_access)
values(nextval('id_sequence'),true,true,true,true,48,59);

insert into tb_menu_access_level as mal(id,access_delete,access_read,access_update,access_write,fk_menu,fk_user_access)
values(nextval('id_sequence'),true,true,true,true,49,59);

insert into tb_menu_access_level as mal(id,access_delete,access_read,access_update,access_write,fk_menu,fk_user_access)
values(nextval('id_sequence'),true,true,true,true,50,59);

insert into tb_menu_access_level as mal(id,access_delete,access_read,access_update,access_write,fk_menu,fk_user_access)
values(nextval('id_sequence'),true,true,true,true,51,59);

insert into tb_menu_access_level as mal(id,access_delete,access_read,access_update,access_write,fk_menu,fk_user_access)
values(nextval('id_sequence'),true,true,true,true,52,59);

insert into tb_menu_access_level as mal(id,access_delete,access_read,access_update,access_write,fk_menu,fk_user_access)
values(nextval('id_sequence'),true,true,true,true,53,59);

insert into tb_menu_access_level as mal(id,access_delete,access_read,access_update,access_write,fk_menu,fk_user_access)
values(nextval('id_sequence'),true,true,true,true,54,59);

insert into tb_menu_access_level as mal(id,access_delete,access_read,access_update,access_write,fk_menu,fk_user_access)
values(nextval('id_sequence'),true,true,true,true,55,59);

insert into tb_menu_access_level as mal(id,access_delete,access_read,access_update,access_write,fk_menu,fk_user_access)
values(nextval('id_sequence'),true,true,true,true,56,59);

insert into tb_menu_access_level as mal(id,access_delete,access_read,access_update,access_write,fk_menu,fk_user_access)
values(nextval('id_sequence'),true,true,true,true,57,59);

select * from tb_menu_access_level;

--INSERT DEFAULT_MENU ON ROLE
--select co.id from tb_company as co where co.code = 'ICM_1';
--select r.id from tb_role as r where r.name = 'ROLE_ICM_ADMIN';
select * from tb_menu as me where me.fk_company = 13;


insert into tb_default_menu_item(id,fk_company,access_delete,fk_menu,access_read,fk_role,access_update,access_write)
values(nextval('id_sequence'),13,true,49,true,8,true,true);

insert into tb_default_menu_item(id,fk_company,access_delete,fk_menu,access_read,fk_role,access_update,access_write)
values(nextval('id_sequence'),13,true,52,true,8,true,true);

insert into tb_default_menu_item(id,fk_company,access_delete,fk_menu,access_read,fk_role,access_update,access_write)
values(nextval('id_sequence'),13,true,47,true,8,true,true);

insert into tb_default_menu_item(id,fk_company,access_delete,fk_menu,access_read,fk_role,access_update,access_write)
values(nextval('id_sequence'),13,true,46,true,8,true,true);

insert into tb_default_menu_item(id,fk_company,access_delete,fk_menu,access_read,fk_role,access_update,access_write)
values(nextval('id_sequence'),13,true,53,true,8,true,true);

insert into tb_default_menu_item(id,fk_company,access_delete,fk_menu,access_read,fk_role,access_update,access_write)
values(nextval('id_sequence'),13,true,45,true,8,true,true);

insert into tb_default_menu_item(id,fk_company,access_delete,fk_menu,access_read,fk_role,access_update,access_write)
values(nextval('id_sequence'),13,true,42,true,8,true,true);

insert into tb_default_menu_item(id,fk_company,access_delete,fk_menu,access_read,fk_role,access_update,access_write)
values(nextval('id_sequence'),13,true,48,true,8,true,true);

insert into tb_default_menu_item(id,fk_company,access_delete,fk_menu,access_read,fk_role,access_update,access_write)
values(nextval('id_sequence'),13,true,43,true,8,true,true);

insert into tb_default_menu_item(id,fk_company,access_delete,fk_menu,access_read,fk_role,access_update,access_write)
values(nextval('id_sequence'),13,true,44,true,8,true,true);

insert into tb_default_menu_item(id,fk_company,access_delete,fk_menu,access_read,fk_role,access_update,access_write)
values(nextval('id_sequence'),13,true,54,true,8,true,true);

insert into tb_default_menu_item(id,fk_company,access_delete,fk_menu,access_read,fk_role,access_update,access_write)
values(nextval('id_sequence'),13,true,55,true,8,true,true);

insert into tb_default_menu_item(id,fk_company,access_delete,fk_menu,access_read,fk_role,access_update,access_write)
values(nextval('id_sequence'),13,true,56,true,8,true,true);

insert into tb_default_menu_item(id,fk_company,access_delete,fk_menu,access_read,fk_role,access_update,access_write)
values(nextval('id_sequence'),13,true,36,true,8,true,true);

insert into tb_default_menu_item(id,fk_company,access_delete,fk_menu,access_read,fk_role,access_update,access_write)
values(nextval('id_sequence'),13,true,37,true,8,true,true);

insert into tb_default_menu_item(id,fk_company,access_delete,fk_menu,access_read,fk_role,access_update,access_write)
values(nextval('id_sequence'),13,true,57,true,8,true,true);

insert into tb_default_menu_item(id,fk_company,access_delete,fk_menu,access_read,fk_role,access_update,access_write)
values(nextval('id_sequence'),13,true,41,true,8,true,true);

insert into tb_default_menu_item(id,fk_company,access_delete,fk_menu,access_read,fk_role,access_update,access_write)
values(nextval('id_sequence'),13,true,38,true,8,true,true);

insert into tb_default_menu_item(id,fk_company,access_delete,fk_menu,access_read,fk_role,access_update,access_write)
values(nextval('id_sequence'),13,true,51,true,8,true,true);

insert into tb_default_menu_item(id,fk_company,access_delete,fk_menu,access_read,fk_role,access_update,access_write)
values(nextval('id_sequence'),13,true,39,true,8,true,true);

insert into tb_default_menu_item(id,fk_company,access_delete,fk_menu,access_read,fk_role,access_update,access_write)
values(nextval('id_sequence'),13,true,50,true,8,true,true);

insert into tb_default_menu_item(id,fk_company,access_delete,fk_menu,access_read,fk_role,access_update,access_write)
values(nextval('id_sequence'),13,true,40,true,8,true,true);

select * from tb_default_menu_item;

--INSERT SYSTEM_PROPERTIES
--select u.id from tb_user as u where u.username= 'icmAdmin';
insert into tb_system_properties(id,item_active,item_key,register_date,fk_register_user,item_value)
values(nextval('id_sequence'),true,'support_page',NOW(),12,'faq');

insert into tb_system_properties(id,item_active,item_key,register_date,fk_register_user,item_value)
values(nextval('id_sequence'),true,'support_page',NOW(),12,'news');

insert into tb_system_properties(id,item_active,item_key,register_date,fk_register_user,item_value)
values(nextval('id_sequence'),true,'support_page',NOW(),12,'gallery');

select * from tb_system_properties;

--INSERT IRAN_COUNTRY in LOCATION_INFO
--select mt.id from tb_management_type mt where mt.code = 'location' and mt.sub_type ='country';

insert into tb_location_info(id,title,zip_code,fk_location_type,fk_parent)
values(nextval('id_sequence'),'iran','098',22,null);

select * from tb_location_info;


--INSERT IRAN_PROVINCE in LOCATION_INFO
--select mt.id from tb_management_type mt where mt.code = 'location' and mt.sub_type ='province';
--select li.id from tb_location_info as li where li.title = 'iran'


insert into tb_location_info(id,title,zip_code,fk_location_type,fk_parent)
values(nextval('id_sequence'),'ardabil','045',23,129);

insert into tb_location_info(id,title,zip_code,fk_location_type,fk_parent)
values(nextval('id_sequence'),'isfahan','031',23,129);

insert into tb_location_info(id,title,zip_code,fk_location_type,fk_parent)
values(nextval('id_sequence'),'alborz','026',23,129);

insert into tb_location_info(id,title,zip_code,fk_location_type,fk_parent)
values(nextval('id_sequence'),'ilam','084',23,129);

insert into tb_location_info(id,title,zip_code,fk_location_type,fk_parent)
values(nextval('id_sequence'),'east_azerbaijan','041',23,129);

insert into tb_location_info(id,title,zip_code,fk_location_type,fk_parent)
values(nextval('id_sequence'),'west_azerbaijan','044',23,129);

insert into tb_location_info(id,title,zip_code,fk_location_type,fk_parent)
values(nextval('id_sequence'),'bushehr','077',23,129);

insert into tb_location_info(id,title,zip_code,fk_location_type,fk_parent)
values(nextval('id_sequence'),'tehran','021',23,129);

insert into tb_location_info(id,title,zip_code,fk_location_type,fk_parent)
values(nextval('id_sequence'),'chaharmahal_va_bakhtiari','038',23,129);

insert into tb_location_info(id,title,zip_code,fk_location_type,fk_parent)
values(nextval('id_sequence'),'south_khorasan','051',23,129);

insert into tb_location_info(id,title,zip_code,fk_location_type,fk_parent)
values(nextval('id_sequence'),'khorasan_razavi','056',23,129);

insert into tb_location_info(id,title,zip_code,fk_location_type,fk_parent)
values(nextval('id_sequence'),'north_khorasan','058',23,129);

insert into tb_location_info(id,title,zip_code,fk_location_type,fk_parent)
values(nextval('id_sequence'),'khuzestan','061',23,129);

insert into tb_location_info(id,title,zip_code,fk_location_type,fk_parent)
values(nextval('id_sequence'),'zanjan','024',23,129);

insert into tb_location_info(id,title,zip_code,fk_location_type,fk_parent)
values(nextval('id_sequence'),'semnan','023',23,129);

insert into tb_location_info(id,title,zip_code,fk_location_type,fk_parent)
values(nextval('id_sequence'),'sistan_and_baluchestan','054',23,129);

insert into tb_location_info(id,title,zip_code,fk_location_type,fk_parent)
values(nextval('id_sequence'),'fars','071',23,129);

insert into tb_location_info(id,title,zip_code,fk_location_type,fk_parent)
values(nextval('id_sequence'),'qazvin','028',23,129);

insert into tb_location_info(id,title,zip_code,fk_location_type,fk_parent)
values(nextval('id_sequence'),'qom','025',23,129);

insert into tb_location_info(id,title,zip_code,fk_location_type,fk_parent)
values(nextval('id_sequence'),'kurdistan','087',23,129);

insert into tb_location_info(id,title,zip_code,fk_location_type,fk_parent)
values(nextval('id_sequence'),'kerman','034',23,129);

insert into tb_location_info(id,title,zip_code,fk_location_type,fk_parent)
values(nextval('id_sequence'),'kermanshah','083',23,129);

insert into tb_location_info(id,title,zip_code,fk_location_type,fk_parent)
values(nextval('id_sequence'),'kohgiluyeh_and_boyer_ahmad','074',23,129);

insert into tb_location_info(id,title,zip_code,fk_location_type,fk_parent)
values(nextval('id_sequence'),'golestan','017',23,129);

insert into tb_location_info(id,title,zip_code,fk_location_type,fk_parent)
values(nextval('id_sequence'),'gilan','013',23,129);

insert into tb_location_info(id,title,zip_code,fk_location_type,fk_parent)
values(nextval('id_sequence'),'lorestan','066',23,129);

insert into tb_location_info(id,title,zip_code,fk_location_type,fk_parent)
values(nextval('id_sequence'),'mazandaran','011',23,129);

insert into tb_location_info(id,title,zip_code,fk_location_type,fk_parent)
values(nextval('id_sequence'),'central','086',23,129);

insert into tb_location_info(id,title,zip_code,fk_location_type,fk_parent)
values(nextval('id_sequence'),'hormozgan','076',23,129);

insert into tb_location_info(id,title,zip_code,fk_location_type,fk_parent)
values(nextval('id_sequence'),'hamadan','081',23,129);

insert into tb_location_info(id,title,zip_code,fk_location_type,fk_parent)
values(nextval('id_sequence'),'yazd','035',23,129);

select * from tb_location_info;


