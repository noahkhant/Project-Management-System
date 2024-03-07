document.addEventListener('DOMContentLoaded', function () {
    Highcharts.ganttChart('gantt-chat-container', {
        title: {
            text: 'Project Timeline'
        },
        xAxis: {
            currentDateIndicator: true,
            min: Date.UTC(2024, 0, 1),
            max: Date.UTC(2024, 11, 31)
        },
        series: [{
            name: 'Project 1',
            data: [{
                name: 'Planned',
                start: Date.UTC(2024, 0, 1),
                end: Date.UTC(2024, 2, 31),
                color: 'rgba(124, 181, 236, 0.2)'
            }, {
                name: 'Actual',
                start: Date.UTC(2024, 1, 1),
                end: Date.UTC(2024, 3, 15),
                color: 'rgba(255, 0, 0, 0.2)'
            }]
        }, {
            name: 'Project 2',
            data: [{
                name: 'Planned',
                start: Date.UTC(2024, 2, 1),
                end: Date.UTC(2024, 5, 30),
                color: 'rgba(124, 181, 236, 0.2)'
            }, {
                name: 'Actual',
                start: Date.UTC(2024, 3, 1),
                end: Date.UTC(2024, 6, 15),
                color: 'rgba(255, 0, 0, 0.2)'
            }]
        }]
    });
});