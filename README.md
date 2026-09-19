# Library Management System

## Overview

This desktop-based Library Management System is built with Java, JavaFX, and MySQL to manage core library operations, circulation tracking, and user account workflows. The primary goal of this repository is to practice clean system architecture and apply practical design patterns, focusing on transitioning from traditional MVC to the [MVCI (Model-View-Controller-Interactor) pattern by PragmaticCoding](https://www.pragmaticcoding.ca/javafx/elements/mvci-quick?utm_source=gemini).

---

## Architectural Focus: MVCI Pattern

The application leverages the **MVCI** pattern to decouple the UI from domain rules and data persistence cleanly:

* **Model (Presentation Model):** Holds observable JavaFX properties representing current UI state and presentation data.
* **View (ViewBuilder):** Constructs the layout and binds controls directly to properties in the presentation model.
* **Controller:** Central coordinator that instantiates components, handles threading/tasks, and relays user actions.
* **Interactor:** Houses business rules and application logic, mediating between presentation models, domain entities, and the database persistence layer (DAOs).



---

## System Scope (MVP)

* **Patron Circulation:** Catalog search across inventory, borrowing, returns, and personal loan tracking.


* **Librarian Administration:** Inventory management (CRUD operations) and system-wide loan monitoring.


* **Administrator Controls:** Role assignments, user account provisioning, and access level enforcement.



---

## Tech Stack

* **Language:** Java 17+


* **UI Toolkit:** JavaFX


* **Database:** MySQL (Relational schema via JDBC/DAO)


* **Build Tool:** Maven



---

> **Note:** This repository is a learning environment and work in progress. It is continuously refined and improved as I explore more into advanced software architecture, system design, and JavaFX design patterns, with further improvements being implemented across dedicated branches.