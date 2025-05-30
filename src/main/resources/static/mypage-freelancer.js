const container = document.querySelector('.mypage-container');

const sectionTitle = document.createElement('h3');
sectionTitle.textContent = '프로젝트 지원 현황';
sectionTitle.style.marginTop = '30px';

const sectionBox = document.createElement('div');
sectionBox.style.padding = '15px';
sectionBox.style.border = '1px solid #ccc';
sectionBox.style.borderRadius = '8px';
sectionBox.style.backgroundColor = '#f4f4f4';
sectionBox.style.marginTop = '10px';

fetch('/api/mypage/applications', {
  method: 'GET',
  credentials: 'same-origin'
})
.then(res => {
  if (!res.ok) throw new Error('응답 실패');
  return res.json();
})
.then(applications => {
  sectionBox.innerHTML = '';
  if (applications.length === 0) {
    sectionBox.textContent = '아직 지원한 프로젝트가 없습니다.';
  } else {
    const cardContainer = document.createElement('div');
    cardContainer.className = 'card-container';

    applications.forEach(app => {
      const card = document.createElement('div');
      card.className = 'card';
      const date = new Date(app.appliedDate).toLocaleString('ko-KR');
      card.textContent = `${app.projectTitle} (지원일: ${date})`;
      cardContainer.appendChild(card);

      card.addEventListener('click', () => {
        if (!app.projectId) {
          console.error('projectId가 제공되지 않았습니다.');
          return;
        }

        fetch(`/api/projects/${app.projectId}`)
          .then(res => res.json())
          .then(detail => {
            showProjectModal(detail);
          })
          .catch(err => {
            console.error('프로젝트 상세 정보 불러오기 실패:', err);
          });
      });
    });

    sectionBox.appendChild(cardContainer);
  }
})
.catch(err => {
  console.error('프로젝트 지원 내역 불러오기 실패:', err);
  sectionBox.textContent = '지원 내역을 불러오는 중 오류가 발생했습니다.';
});

container.appendChild(sectionTitle);
container.appendChild(sectionBox);
