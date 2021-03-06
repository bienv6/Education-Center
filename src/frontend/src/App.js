import {useEffect, useState} from 'react'
import {deleteStudent, getAllStudents} from "./client";
import {Avatar, Badge, Breadcrumb, Button, Empty, Layout, Menu, Popconfirm, Radio, Spin, Table, Image, Tag} from 'antd';


import {
    DesktopOutlined,
    FileOutlined,
    LoadingOutlined,
    PieChartOutlined,
    PlusOutlined,
    TeamOutlined,
    UserOutlined
} from '@ant-design/icons';
import StudentDrawerForm from "./StudentDrawerForm";

import './App.css';
import {errorNotification, successNotification} from "./Notification";

const {Header, Content, Footer, Sider} = Layout;
const {SubMenu} = Menu;


const antIcon = <LoadingOutlined style={{fontSize: 24}} spin/>;

function App() {
    const [students, setStudents] = useState([]);
    const [collapsed, setCollapsed] = useState(false);
    const [fetching, setFetching] = useState(true);
    const [showDrawer, setShowDrawer] = useState(false);

    const fetchStudents = () =>
        getAllStudents()
            .then(res => res.json())
            .then(data => {
                console.log(data);
                setStudents(data);
                setFetching(false)

            }).catch(err => {
            err.response.json().then(res => {
                console.log(res);
                errorNotification("there was an issue", `${res.message} [${res.status}]`)
            });
        }).finally(() => setFetching(false));

    const removeStudent = (studentId, callbackFunc) => {
        deleteStudent(studentId).then(() => {
            successNotification("Student Deleted", `student with ID ${studentId} was deleted `);
            callbackFunc();
        })
    }

    useEffect(() => {
        console.log("component is mounted");
        fetchStudents();
    }, []);

    const TheAvatar = ({name}) => {
        let trim = name.split(" ");
        if (trim[1]) {
            return <Avatar>
                {`${trim[0].charAt(0)}${trim[1].charAt(0)}`}
            </Avatar>
        } else {
            return <Avatar>
                {`${trim[0].charAt(0)}${trim[0].charAt(trim[0].length - 1)}`}
            </Avatar>
        }

    }

    const columns = fetchStudents => [
        {
            title: '',
            dataIndex: 'avatar',
            key: 'avatar',
            render: (text, student) =>
                <TheAvatar name={student.name}/>
        },
        {
            title: 'Id',
            dataIndex: 'id',
            key: 'id',
        },
        {
            title: 'Name',
            dataIndex: 'name',
            key: 'name',
        },
        {
            title: 'Email',
            dataIndex: 'email',
            key: 'email',
        },
        {
            title: 'Gender',
            dataIndex: 'gender',
            key: 'gender',
        },
        {
            title: 'Actions',
            dataIndex: 'actions',
            key: 'actions',
            render: (text, student) =>
                <Radio.Group>
                    <Radio.Button value="small">Edit</Radio.Button>
                    <Popconfirm
                        placement={"topRight"}
                        title={`Are you sure you want to delete ${student.name} ?`}
                        onConfirm={() => removeStudent(student.id, fetchStudents)}
                        okText={"Yes"}
                        cancelText={"No"}>
                        <Radio.Button value="small">Delete</Radio.Button>
                    </Popconfirm>
                </Radio.Group>
        }
    ];


    const renderStudents = () => {
        if (fetching) {
            return <Spin indicator={antIcon}/>
        }
        if (students.length <= 0) {
            return <>
                <Button
                    onClick={() => setShowDrawer(!showDrawer)}
                    type="primary" shape="round" icon={<PlusOutlined/>} size="small">
                    Add New Student
                </Button>
                <StudentDrawerForm
                    showDrawer={showDrawer}
                    setShowDrawer={setShowDrawer}
                    fetchStudents={fetchStudents}
                />
                <Empty/>
            </>
        }

        return <>
            <StudentDrawerForm
                showDrawer={showDrawer}
                setShowDrawer={setShowDrawer}
                fetchStudents={fetchStudents}
            />
            <Table
                dataSource={students}
                columns={columns(fetchStudents)}
                bordered
                title={() =>
                    <>
                        <Tag style={{marginLeft: "15px"}}> Number of students </Tag>
                        <Badge count={students.length} overflowCount={2050} className="site-badge-count-4"/>
                        <br/>
                        <br/>
                        <Button
                            onClick={() => setShowDrawer(!showDrawer)}
                            type="primary" shape="round" icon={<PlusOutlined/>} size="small">
                            Add New Student
                        </Button>
                    </>
                }
                pagination={{pageSize: 50}}
                scroll={{y: 500}}
                rowKey={student => student.id}
            />
        </>

    }

    return <Layout style={{minHeight: '100vh'}}>
        <Sider collapsible collapsed={collapsed}
               onCollapse={setCollapsed}>
            <div className="logo"/>
            <Menu theme="dark" defaultSelectedKeys={['1']} mode="inline">
                <Menu.Item key="1" icon={<PieChartOutlined/>}>
                    Option 1
                </Menu.Item>
                <Menu.Item key="2" icon={<DesktopOutlined/>}>
                    Option 2
                </Menu.Item>
                <SubMenu key="sub1" icon={<UserOutlined/>} title="User">
                    <Menu.Item key="3">Tom</Menu.Item>
                    <Menu.Item key="4">Bill</Menu.Item>
                    <Menu.Item key="5">Alex</Menu.Item>
                </SubMenu>
                <SubMenu key="sub2" icon={<TeamOutlined/>} title="Team">
                    <Menu.Item key="6">Team 1</Menu.Item>
                    <Menu.Item key="8">Team 2</Menu.Item>
                </SubMenu>
                <Menu.Item key="9" icon={<FileOutlined/>}>
                    Files
                </Menu.Item>
            </Menu>
        </Sider>
        <Layout className="site-layout">
            <Header className="site-layout-background" style={{padding: 0}}/>
            <Content style={{margin: '0 16px'}}>
                <Breadcrumb style={{margin: '16px 0'}}>
                    <Breadcrumb.Item>User</Breadcrumb.Item>
                    <Breadcrumb.Item>Bill</Breadcrumb.Item>
                </Breadcrumb>
                <div className="site-layout-background" style={{padding: 24, minHeight: 360}}>
                    {renderStudents()}
                </div>
            </Content>
            <Footer style={{textAlign: 'center'}}>

                <Image
                width={45}
                height={45}
                src={"https://user-images.githubusercontent.com/31675961/116484588-5fee3580-a857-11eb-9667-ae2f9d537be8.png"}>
                    <span>  By BienCorp</span>
                    </Image>





                </Footer>
        </Layout>
    </Layout>
}

export default App;
