import { useState } from "react";
import TrabajadorList from "./components/TrabajadorList";
import "./App.css";

function App() {
  // Estado para controlar qué sección se muestra en el contenido principal
  const [view, setView] = useState("trabajadores");

  const renderContent = () => {
    switch (view) {
      case "trabajadores":
        // Este componente utiliza la lógica de trabajadores que ya tienes implementada
        return <TrabajadorList />;
      
      case "facturas":
        return (
          <div className="placeholder-view">
            <h2>Gestión de Facturas</h2>
            <p>Módulo de facturación en desarrollo. Aquí podrás ver montos y estados.</p>
          </div>
        );
      
      case "departamentos":
        return (
          <div className="placeholder-view">
            <h2>Estructura Organizacional</h2>
            <p>Módulo de departamentos. Visualiza la jerarquía de la empresa.</p>
          </div>
        );
      
      case "cola":
        return (
          <div className="placeholder-view">
            <h2>Cola de Pagos</h2>
            <p>Módulo de priorización de pagos según el orden de llegada.</p>
          </div>
        );
      
      default:
        return <TrabajadorList />;
    }
  };

  return (
    <div className="app-container">
      {/* Sidebar: Barra lateral con navegación temática */}
      <aside className="sidebar">
        <h2>Facturacion-SAJ</h2>
        <nav className="nav-menu">
          <div 
            className={`nav-item ${view === "facturas" ? "active" : ""}`} 
            data-view="facturas"
            onClick={() => setView("facturas")}
          >
            <span>Facturas</span>
          </div>
          
          <div 
            className={`nav-item ${view === "trabajadores" ? "active" : ""}`} 
            data-view="trabajadores"
            onClick={() => setView("trabajadores")}
          >
            <span>Trabajadores</span>
          </div>
          
          <div 
            className={`nav-item ${view === "departamentos" ? "active" : ""}`} 
            data-view="departamentos"
            onClick={() => setView("departamentos")}
          >
            <span>Departamentos</span>
          </div>
          
          <div 
            className={`nav-item ${view === "cola" ? "active" : ""}`} 
            data-view="cola"
            onClick={() => setView("cola")}
          >
            <span>Cola de Pagos</span>
          </div>
        </nav>
      </aside>

      {/* Main: Área donde se cargan los componentes dinámicamente */}
      <main className="main-content">
        <header style={{ marginBottom: "2rem" }}>
          <h1>Sistema de Gestión</h1>
          <p style={{ color: "var(--text-dark)", opacity: 0.7 }}>
            Bienvenido al panel de control de Facturación y Nómina.
          </p>
        </header>

        {/* Contenedor con estilo de tarjeta para las tablas o formularios */}
        <section className="data-table-container">
          {renderContent()}
        </section>
      </main>
