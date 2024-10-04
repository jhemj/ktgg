
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import ProjectProjectManager from "./components/listers/ProjectProjectCards"
import ProjectProjectDetail from "./components/listers/ProjectProjectDetail"

import InterestInterestManager from "./components/listers/InterestInterestCards"
import InterestInterestDetail from "./components/listers/InterestInterestDetail"

import ConsultationConsultationManager from "./components/listers/ConsultationConsultationCards"
import ConsultationConsultationDetail from "./components/listers/ConsultationConsultationDetail"

import AlarmAlarmManager from "./components/listers/AlarmAlarmCards"
import AlarmAlarmDetail from "./components/listers/AlarmAlarmDetail"

import SalesmanmatchSalesmanMatchManager from "./components/listers/SalesmanmatchSalesmanMatchCards"
import SalesmanmatchSalesmanMatchDetail from "./components/listers/SalesmanmatchSalesmanMatchDetail"


export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/projects/projects',
                name: 'ProjectProjectManager',
                component: ProjectProjectManager
            },
            {
                path: '/projects/projects/:id',
                name: 'ProjectProjectDetail',
                component: ProjectProjectDetail
            },

            {
                path: '/interests/interests',
                name: 'InterestInterestManager',
                component: InterestInterestManager
            },
            {
                path: '/interests/interests/:id',
                name: 'InterestInterestDetail',
                component: InterestInterestDetail
            },

            {
                path: '/consultations/consultations',
                name: 'ConsultationConsultationManager',
                component: ConsultationConsultationManager
            },
            {
                path: '/consultations/consultations/:id',
                name: 'ConsultationConsultationDetail',
                component: ConsultationConsultationDetail
            },

            {
                path: '/alarms/alarms',
                name: 'AlarmAlarmManager',
                component: AlarmAlarmManager
            },
            {
                path: '/alarms/alarms/:id',
                name: 'AlarmAlarmDetail',
                component: AlarmAlarmDetail
            },

            {
                path: '/salesmanmatches/salesmanMatches',
                name: 'SalesmanmatchSalesmanMatchManager',
                component: SalesmanmatchSalesmanMatchManager
            },
            {
                path: '/salesmanmatches/salesmanMatches/:id',
                name: 'SalesmanmatchSalesmanMatchDetail',
                component: SalesmanmatchSalesmanMatchDetail
            },



    ]
})
