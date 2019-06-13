using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Web;

namespace Zadanie6.Models
{
    public class MusicDbContext : DbContext
    {
        public MusicDbContext() : base("DefaultConnection") { }
        public DbSet<Song> Songs { get; set; }
        public DbSet<Genre> Genres { get; set; }
    }
}