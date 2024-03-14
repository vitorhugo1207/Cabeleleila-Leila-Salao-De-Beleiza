'use client'

import checkAuth from "@/_auth/checkAuth";
import React, { useEffect, useState } from "react";
import { Calendar } from 'primereact/calendar';
import "primereact/resources/themes/lara-light-cyan/theme.css";
import { TreeTable } from 'primereact/treetable';
import { Column } from 'primereact/column';
import getMyHeaders from '@/app/scheduling/getSchedules.js'

export default function Page() {
    checkAuth();

    const [date, setDate] = useState(null);
    const [nodes, setNodes] = useState([]); 

    useEffect(() => {
        const myHeaders = getMyHeaders();

        const fetchData = async () => {
            const response = await fetch("http://127.0.0.1:8080/schedule/", {
                method: "GET",
                headers: "",
                redirect: "follow"
            }).then(response => response.json())
            .then(result => {
                    const { id, accepted, scheduled, service } = result;
                    const children = service.map(s => ({
                        key: `${id}-${s.id}`, id: s.id, name: s.name, about: s.about
                    }));
                return { key: id.toString(), id, accepted, scheduled, children }
            .catch(error => reject(error));
            });
            setNodes(response);
        }
        fetchData();
    }, [])

    return (
        <div className="flex flex-col items-center mx-80">
            <form className="flex flex-row items-center gap-10">
                <div className="card flex justify-content-center">
                    <Calendar value={date} onChange={(e) => setDate(e.value)} inline showWeek showTime hourFormat="24" />
                </div>
                <div className="card">
                    <TreeTable value={nodes} tableStyle={{ minWidth: '50rem' }}>
                        <Column field="scheduled" header="Dia" expander></Column>
                        <Column field="scheduled" header="Horario"></Column>
                        <Column field="accepted" header="Status"></Column>
                    </TreeTable>
                </div>
            </form>
        </div>
    )
}
