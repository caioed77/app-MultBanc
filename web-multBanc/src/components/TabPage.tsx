export default function TabPage({selectedTab}) {
  
  const tabContent = {
    tab1: 'Conteúdo da primeira aba',
    tab2: 'Conteúdo da segunda aba',  
  };
  return (
    <div className="p-4">
      <div className="bg-white p-4 rounded shadow">
        Conteúdo da primeira aba
      </div>
      <div className="bg-white p-4 rounded shadow mt-4">
      {tabContent[selectedTab]}
      </div>
    </div>
  );
}