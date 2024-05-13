import { useState } from "react";
import { Outlet } from "react-router-dom";
import Navbar from "../Navbar";
import Sidebar from "../Sidebar";

const DashboardLayout = () => {
  const [sidebarOpen, setSidebarOpen] = useState(true);

  return (
    <div style={{ display: 'flex', maxHeight: '100vh', overflow: 'hidden', flexDirection: "row" }}>
      {/* Sidebar */}
      <div style={{ width: sidebarOpen ? '250px' : '50px' }}>
        <Sidebar setSidebarOpen={setSidebarOpen} sidebarOpen={sidebarOpen} />
      </div>

      {/* Main Content Area */}
      <div style={{ display: 'flex', flexDirection: 'column', width: "100%" }}>
        {/* Navbar */}
        <Navbar />

        {/* Main Content */}
        <div style={{ flex: 1, overflow: 'auto' }}>
          <Outlet />
        </div>
      </div>
    </div>
  );
};

export default DashboardLayout;
